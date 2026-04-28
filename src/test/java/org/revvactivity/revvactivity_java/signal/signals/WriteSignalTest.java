package org.revvactivity.revvactivity_java.signal.signals;

import org.junit.jupiter.api.Test;
import org.revvactivity.revvactivity_java.signal.listeners.SignalChangeListener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void changeValue() {
        final WriteSignal<String> writeSignal = new WriteSignal<>();

        final SignalChangeListener<String> changeListener = mock();

        assertThat(writeSignal.getValue())
                .isNull();

        writeSignal.onChange(changeListener);

        final String firstValue = "Hello";

        writeSignal.changeValue(v -> firstValue);

        assertThat(writeSignal.getValue())
                .isEqualTo(firstValue);

        verify(changeListener, times(1))
                .onChange(any(), any());

        writeSignal.changeValue(v -> v);

        assertThat(writeSignal.getValue())
                .isEqualTo(firstValue);

        verify(changeListener, times(1))
                .onChange(any(), any());

        final String appendix = ", World!";

        writeSignal.changeValue(v -> v + appendix);

        assertThat(writeSignal.getValue())
                .isEqualTo(firstValue + appendix);

        verify(changeListener, times(2))
                .onChange(any(), any());
    }
}
