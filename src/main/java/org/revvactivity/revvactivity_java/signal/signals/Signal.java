package org.revvactivity.revvactivity_java.signal.signals;

import org.revvactivity.revvactivity_java.signal.listeners.SignalUpdateListener;
import org.revvactivity.revvactivity_java.signal.listeners.SignalChangeListener;

import java.util.LinkedList;
import java.util.List;

public class Signal<T> {
    private T value;
    private final List<SignalChangeListener<? super T>> updateListeners;

    protected Signal() {
        super();
        this.updateListeners = new LinkedList<>();
    }

    public Signal(final T value) {
        this();
        this.value = value;
    }
    
    public T getValue() {
        return this.value;
    }

    protected void setValue(final T value) {
        final T oldValue = this.value;
        this.value = value;

        notifyUpdateListeners(oldValue);
    }

    protected void notifyUpdateListeners(final T oldValue) {
        this.updateListeners.forEach(updateListener -> updateListener.onChange(oldValue, this.value));
    }

    public void onUpdate(final SignalUpdateListener<? super T> updateListener) {
        this.updateListeners.add((o, n) -> updateListener.onUpdate(n));
    }

    public void onUpdate(final SignalChangeListener<? super T> changeListener) {
        this.updateListeners.add(changeListener);
    }
}
