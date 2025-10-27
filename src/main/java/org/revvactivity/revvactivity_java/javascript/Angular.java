package org.revvactivity.revvactivity_java.javascript;

import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

public final class Angular extends JavaScriptFramework {
    private Angular() throws IllegalStateException {
        super();
    }

    public static <T> WriteSignal<T> signal(final T value) {
        return JavaScriptFramework.state(value);
    }

    public static <T> Signal<T> computed(final SignalDerived<T> derived) {
        return JavaScriptFramework.derived(derived);
    }

    public static void effect(final SignalEffect effect) {
        JavaScriptFramework.effect(effect);
    }
}
