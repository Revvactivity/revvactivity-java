package org.revvactivity.revvactivity_java.javascript;

import org.revvactivity.revvactivity_java.signal.functions.SignalDerived;
import org.revvactivity.revvactivity_java.signal.functions.SignalEffect;
import org.revvactivity.revvactivity_java.signal.signals.Signal;
import org.revvactivity.revvactivity_java.signal.signals.WriteSignal;

import java.util.function.Consumer;
import java.util.function.Function;

class AngularTest extends JavaScriptFrameworkTest {
    @Override
    protected Class<? extends JavaScriptFramework> getType() {
        return Angular.class;
    }

    @Override
    protected <T> Function<T, WriteSignal<T>> getStateFunction() {
        return Angular::signal;
    }

    @Override
    protected <T> Function<SignalDerived<T>, Signal<T>> getDerivedFunction() {
        return Angular::computed;
    }

    @Override
    protected Consumer<SignalEffect> getEffectFunction() {
        return Angular::effect;
    }
}