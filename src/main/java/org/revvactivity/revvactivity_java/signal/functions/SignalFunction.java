package org.revvactivity.revvactivity_java.signal.functions;

import org.revvactivity.revvactivity_java.signal.listeners.SignalUpdateListener;
import org.revvactivity.revvactivity_java.signal.signals.Signal;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface SignalFunction {
    default void onUpdateSignals(final SignalUpdateListener<Object> updateListener) {
        final Set<Signal<?>> signals = getSignals();
        signals.forEach(signal -> signal.onUpdate(updateListener));
    }

    private Set<Signal<?>> getSignals() {
        final Set<Field> signalFields = getSignalFields();
        return getSignalsFromFields(signalFields);
    }

    private Set<Field> getSignalFields() {
        final Set<Field> signalFields = new HashSet<>();
        for (final Field f : getClass().getDeclaredFields()) {
            if (Signal.class.isAssignableFrom(f.getType())) {
                signalFields.add(f);
            }
        }
        return signalFields;
    }

    private Set<Signal<?>> getSignalsFromFields(final Set<Field> signalFields) {
        return signalFields.stream()
                .map(this::getSignalFromField)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    private Optional<Signal<?>> getSignalFromField(final Field field) {
        try {
            field.setAccessible(true);
            final Object value = field.get(this);
            if (value instanceof Signal<?> signal) {
                return Optional.of(signal);
            }
        } catch (final IllegalAccessException ignored) {
        }
        return Optional.empty();
    }
}
