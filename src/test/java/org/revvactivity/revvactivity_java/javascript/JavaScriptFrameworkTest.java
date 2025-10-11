package org.revvactivity.revvactivity_java.javascript;

import org.junit.jupiter.api.Test;
import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

import java.lang.reflect.Constructor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract class JavaScriptFrameworkTest {
    protected abstract Class<? extends JavaScriptFramework> getType();

    protected abstract <T> Function<T, WriteSignal<T>> getStateFunction();

    protected abstract <T> Function<SignalDerived<T>, Signal<T>> getDerivedFunction();

    protected abstract Consumer<SignalEffect> getEffectFunction();

    @Test
    void testConstructor() {
        assertThatThrownBy(() -> {
            final Constructor<?> defaultConstructor = getType().getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            defaultConstructor.newInstance();
        })
                .hasCauseInstanceOf(IllegalStateException.class);
    }

    @Test
    void state() {
        final String value = "Hello, World";

        final WriteSignal<String> expected = new WriteSignal<>(value);

        final Function<String, WriteSignal<String>> state = getStateFunction();
        final WriteSignal<String> actual = state.apply(value);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void derived() {
        final int factor1 = 10;
        int factor2 = 20;

        final WriteSignal<Integer> writeSignal = new WriteSignal<>(factor2);

        final Function<SignalDerived<Integer>, Signal<Integer>> derived = getDerivedFunction();
        final Signal<Integer> derivedSignal = derived.apply(() -> writeSignal.getValue() * factor1);

        assertThat(derivedSignal.getValue())
                .isEqualTo(factor2 * factor1);

        factor2 = 30;
        writeSignal.setValue(factor2);

        assertThat(derivedSignal.getValue())
                .isEqualTo(factor2 * factor1);

        factor2 = 40;
        writeSignal.setValue(factor2);

        assertThat(derivedSignal.getValue())
                .isEqualTo(factor2 * factor1);
    }

    @Test
    void effect() {
        int counter = 0;
        final AtomicInteger atomicCounter = new AtomicInteger(counter);
        final WriteSignal<String> writeSignal = new WriteSignal<>();

        assertThat(atomicCounter.get())
                .isEqualTo(counter);

        final Consumer<SignalEffect> effect = getEffectFunction();
        effect.accept(() -> {
            writeSignal.getValue();
            atomicCounter.incrementAndGet();
        });

        counter++;
        assertThat(atomicCounter.get())
                .isEqualTo(counter);

        writeSignal.setValue("Foo");
        counter++;
        assertThat(atomicCounter.get())
                .isEqualTo(counter);

        writeSignal.setValue("Bar");
        counter++;
        assertThat(atomicCounter.get())
                .isEqualTo(counter);
    }
}