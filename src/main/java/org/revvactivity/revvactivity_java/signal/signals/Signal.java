package org.revvactivity.revvactivity_java.signal.signals;

public class Signal<T> {
    private T value;

    protected Signal() {
        super();
    }

    public Signal(final T value) {
        this();
        this.value = value;
    }
    
    public T getValue() {
        return this.value;
    }

    protected void setValue(final T value) {
        this.value = value;
    }
}
