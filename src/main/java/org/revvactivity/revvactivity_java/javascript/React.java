package org.revvactivity.revvactivity_java.javascript;

import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

public final class React {
    private React() throws IllegalStateException {
        throw new IllegalStateException();
    }

    public static <T> WriteSignal<T> useState(final T value) {
        return Svelte.state(value);
    }

    public static <T> Signal<T> useMemo(final SignalDerived<T> memo) {
        return Svelte.derived(memo);
    }

    public static void useEffect(final SignalEffect effect) {
        Svelte.effect(effect);
    }
}
