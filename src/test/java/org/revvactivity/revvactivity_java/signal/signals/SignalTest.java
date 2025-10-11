package org.revvactivity.revvactivity_java.signal.signals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.revvactivity.revvactivity_java.signal.listeners.SignalChangeListener;
import org.revvactivity.revvactivity_java.signal.listeners.SignalUpdateListener;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SignalTest {
    private static final String FOO = "Foo";
    private static final String BAR = "Bar";
    private static final String BAZ = "Baz";

    private String firstValue;
    private String secondValue;
    private Signal<String> firstSignal;
    private Signal<String> secondSignal;
    private SignalUpdateListener<String> updateListener;
    private SignalChangeListener<String> changeListener;

    @BeforeEach
    void setUp() {
        this.firstValue = FOO;
        this.secondValue = BAR;

        this.firstSignal = getSignal(this.firstValue);
        this.secondSignal = new Signal<>(this.secondValue);

        this.updateListener = mock();
        this.changeListener = mock();
    }

    protected Signal<String> getSignal(final String value) {
        return new Signal<>(value);
    }

    @Test
    void getValue() {
        assertThat(this.firstSignal.getValue())
                .isEqualTo(this.firstValue);
    }

    private static Stream<Arguments> setValue() {
        return Stream.of(
                Arguments.of(FOO),
                Arguments.of(BAR)
        );
    }

    @ParameterizedTest
    @MethodSource
    void setValue(final String value) {
        assertThat(this.firstSignal.getValue())
                .isEqualTo(this.firstValue);

        this.firstSignal.onUpdate(this.updateListener);
        this.firstSignal.onChange(this.changeListener);

        this.firstSignal.setValue(value);

        assertThat(this.firstSignal.getValue())
                .isEqualTo(value);

        verify(this.updateListener, times(1))
                .onUpdate(value);
        if (!this.firstValue.equals(value)) {
            verify(this.changeListener, times(1))
                    .onChange(this.firstValue, value);
        }
    }

    @Test
    void notifyUpdateListeners() {
        testUpdateListener();
    }

    private void testUpdateListener() {
        this.firstSignal.onUpdate(this.updateListener);

        this.firstSignal.notifyUpdateListeners(this.secondValue);

        verify(this.updateListener, times(1))
                .onUpdate(this.firstValue);
    }

    @Test
    void notifyChangeListeners() {
        testChangeListener();
    }

    private void testChangeListener() {
        this.firstSignal.onChange(this.changeListener);

        this.firstSignal.notifyChangeListeners(this.secondValue);

        verify(this.changeListener, times(1))
                .onChange(this.secondValue, this.firstValue);
    }

    @Test
    void onUpdate_updateListener() {
        testUpdateListener();
    }

    @Test
    void onUpdate_changeListener() {
        this.firstSignal.onUpdate(this.changeListener);

        this.firstSignal.notifyUpdateListeners(this.secondValue);

        verify(this.changeListener, times(1))
                .onChange(this.secondValue, this.firstValue);
    }

    @Test
    void onChange_updateListener() {
        this.firstSignal.onChange(this.updateListener);

        this.firstSignal.notifyChangeListeners(this.secondValue);

        verify(this.updateListener, times(1))
                .onUpdate(this.firstValue);
    }

    @Test
    void onChange_changeListener() {
        testChangeListener();
    }

    @Test
    void bindValueFrom() {
        bindingTest(
                this.firstSignal,
                this.secondSignal,
                this.firstValue,
                this.secondValue,
                () -> this.firstSignal.bindValueFrom(this.secondSignal)
        );
    }

    @Test
    void bindValueTo() {
        bindingTest(
                this.secondSignal,
                this.firstSignal,
                this.secondValue,
                this.firstValue,
                () -> this.firstSignal.bindValueTo(this.secondSignal)
        );
    }

    private void bindingTest(final Signal<String> signalOne,
                             final Signal<String> signalTwo,
                             final String valueOne,
                             final String valueTwo,
                             final Runnable binder) {
        assertThat(signalOne.getValue())
                .isEqualTo(valueOne);

        binder.run();

        assertThat(signalOne.getValue())
                .isEqualTo(valueTwo);

        final String valueThree = BAZ;

        signalTwo.setValue(valueThree);

        assertThat(signalOne.getValue())
                .isEqualTo(valueThree);
    }
}