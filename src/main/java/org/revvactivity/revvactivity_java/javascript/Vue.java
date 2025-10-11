package org.revvactivity.revvactivity_java.javascript;

import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

public final class Vue extends JavaScriptFramework {
    private Vue() throws IllegalStateException {
        super();
    }

    public static <T> WriteSignal<T> ref(final T value) {
        return JavaScriptFramework.state(value);
    }

    public static <T> Signal<T> computed(final SignalDerived<T> computation) {
        return JavaScriptFramework.derived(computation);
    }

    public static void watchEffect(final SignalEffect effect) {
        JavaScriptFramework.effect(effect);
    }
}
