package org.revvactivity.revvactivity_java.signal.listeners;

@FunctionalInterface
public interface SignalChangeListener<T> {
    void onChange(final T oldValue, final T newValue);
}
