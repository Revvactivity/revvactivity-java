package org.revvactivity.revvactivity_java.javascript;

import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

public final class Vue {
    private Vue() throws IllegalStateException {
        throw new IllegalStateException();
    }

    public static <T> WriteSignal<T> ref(final T value) {
        return Svelte.state(value);
    }

    public static <T> Signal<T> computed(final SignalDerived<T> computation) {
        return Svelte.derived(computation);
    }

    public static void watchEffect(final SignalEffect effect) {
        Svelte.effect(effect);
    }
}
