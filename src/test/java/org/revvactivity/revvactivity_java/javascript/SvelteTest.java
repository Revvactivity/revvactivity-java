package org.revvactivity.revvactivity_java.javascript;

import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

import java.util.function.Consumer;
import java.util.function.Function;

class SvelteTest extends JavaScriptFrameworkTest {
    @Override
    protected Class<? extends JavaScriptFramework> getType() {
        return Svelte.class;
    }

    @Override
    protected <T> Function<T, WriteSignal<T>> getStateFunction() {
        return Svelte::state;
    }

    @Override
    protected <T> Function<SignalDerived<T>, Signal<T>> getDerivedFunction() {
        return Svelte::derived;
    }

    @Override
    protected Consumer<SignalEffect> getEffectFunction() {
        return Svelte::effect;
    }
}