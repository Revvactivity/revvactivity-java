package org.revvactivity.revvactivity_java.signal.signals;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WriteSignalTest extends SignalTest {
    @Override
    protected Signal<String> getSignal(final String value) {
        return new WriteSignal<>(value);
    }

    @Test
    void updateValue() {
        final WriteSignal<String> writeSignal = new WriteSignal<>();

        assertThat(writeSignal.getValue())
                .isNull();

        final String firstValue = "Hello";

        writeSignal.updateValue(v -> firstValue);

        assertThat(writeSignal.getValue())
                .isEqualTo(firstValue);

        final String appendix = ", World!";

        writeSignal.updateValue(v -> v + appendix);

        assertThat(writeSignal.getValue())
                .isEqualTo(firstValue + appendix);
    }
}