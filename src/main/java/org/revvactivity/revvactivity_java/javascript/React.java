package org.revvactivity.revvactivity_java.javascript;

import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

public final class React extends JavaScriptFramework {
    private React() throws IllegalStateException {
        super();
    }

    public static <T> WriteSignal<T> useState(final T value) {
        return JavaScriptFramework.state(value);
    }

    public static <T> Signal<T> useMemo(final SignalDerived<T> memo) {
        return JavaScriptFramework.derived(memo);
    }

    public static void useEffect(final SignalEffect effect) {
        JavaScriptFramework.effect(effect);
    }
}
