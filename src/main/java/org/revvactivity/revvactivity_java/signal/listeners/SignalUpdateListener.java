package org.revvactivity.revvactivity_java.signal.listeners;

@FunctionalInterface
public interface SignalUpdateListener<T> extends SignalListener<T> {
    void onUpdate(final T value);
}
