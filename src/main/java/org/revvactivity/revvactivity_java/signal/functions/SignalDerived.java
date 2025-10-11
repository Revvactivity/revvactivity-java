package org.revvactivity.revvactivity_java.signal.functions;

@FunctionalInterface
public interface SignalDerived<T> extends SignalFunction {
    T get();
}
