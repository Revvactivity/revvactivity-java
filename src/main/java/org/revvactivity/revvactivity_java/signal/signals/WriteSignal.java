package org.revvactivity.revvactivity_java.signal.signals;

import java.util.function.UnaryOperator;

public class WriteSignal<T> extends Signal<T> {
    public WriteSignal() {
        super();
    }

    public WriteSignal(final T value) {
        super(value);
    }

    @Override
    public void setValue(final T value) {
        super.setValue(value);
    }

    public void updateValue(final UnaryOperator<T> updateFunction) {
        setValue(updateFunction.apply(getValue()));
    }
}
