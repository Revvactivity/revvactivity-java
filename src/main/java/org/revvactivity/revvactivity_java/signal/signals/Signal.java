package org.revvactivity.revvactivity_java.signal.signals;

import org.revvactivity.revvactivity_java.signal.listeners.SignalUpdateListener;
import org.revvactivity.revvactivity_java.signal.listeners.SignalChangeListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Signal<T> {
    private T value;
    private final List<SignalChangeListener<? super T>> updateListeners;
    private final List<SignalChangeListener<? super T>> changeListeners;

    protected Signal() {
        super();
        this.updateListeners = new LinkedList<>();
        this.changeListeners = new LinkedList<>();
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
        if (!Objects.equals(oldValue, value)) {
            notifyChangeListeners(oldValue);
        }
    }

    protected void notifyUpdateListeners(final T oldValue) {
        this.updateListeners.forEach(updateListener -> updateListener.onChange(oldValue, this.value));
    }

    protected void notifyChangeListeners(final T oldValue) {
        this.changeListeners.forEach(changeListener -> changeListener.onChange(oldValue, this.value));
    }

    public void onUpdate(final SignalUpdateListener<? super T> updateListener) {
        this.updateListeners.add((o, n) -> updateListener.onUpdate(n));
    }

    public void onUpdate(final SignalChangeListener<? super T> changeListener) {
        this.updateListeners.add(changeListener);
    }

    public void onChange(final SignalUpdateListener<? super T> updateListener) {
        this.changeListeners.add((o, n) -> updateListener.onUpdate(n));
    }

    public void onChange(final SignalChangeListener<? super T> changeListener) {
        this.changeListeners.add(changeListener);
    }

    public void bindValueFrom(final Signal<? extends T> from) {
        from.onChange(this::setValue);
        setValue(from.getValue());
    }

    public void bindValueTo(final Signal<? super T> to) {
        onChange(to::setValue);
        to.setValue(getValue());
    }
}
