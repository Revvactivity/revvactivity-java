package org.revvactivity.revvactivity_java.signal.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.revvactivity.revvactivity_java.signal.listeners.SignalUpdateListener;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

import static org.mockito.Mockito.*;

class SignalFunctionTest {
    private SignalFunction signalFunction;
    private WriteSignal<String> signal1;
    private WriteSignal<String> signal2;
    private SignalUpdateListener<Object> updateListener;

    @BeforeEach
    void setUp() {
        this.signal1 = new WriteSignal<>();
        this.signal2 = new WriteSignal<>();
        this.updateListener = mock();

        this.signalFunction = new SignalFunction() {
            private final Signal<?> s1 = signal1;
            private final Signal<?> s2 = signal2;
        };
    }

    @Test
    void onUpdateSignals() {
        this.signalFunction.onUpdateSignals(this.updateListener);

        final String signal1Value = "Foo";
        this.signal1.setValue(signal1Value);
        verify(this.updateListener, times(1))
                .onUpdate(signal1Value);

        final String signal2Value = "Bar";
        this.signal2.setValue(signal2Value);
        verify(this.updateListener, times(1))
                .onUpdate(signal2Value);
    }
}