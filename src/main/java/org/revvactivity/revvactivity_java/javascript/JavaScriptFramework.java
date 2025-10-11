package org.revvactivity.revvactivity_java.javascript;

import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

public abstract class JavaScriptFramework {
    protected JavaScriptFramework() throws IllegalStateException {
        throw new IllegalStateException();
    }

    protected static <T> WriteSignal<T> state(final T value) {
        return new WriteSignal<>(value);
    }

    protected static <T> Signal<T> derived(final SignalDerived<T> derived) {
        final WriteSignal<T> writeSignal = new WriteSignal<>(derived.get());
        derived.onUpdateSignals(v -> writeSignal.setValue(derived.get()));

        final Signal<T> signal = new Signal<>(derived.get());
        signal.bindValueFrom(writeSignal);
        return signal;
    }

    protected static void effect(final SignalEffect effect) {
        effect.onUpdateSignals(v -> effect.execute());
        effect.execute();
    }
}
