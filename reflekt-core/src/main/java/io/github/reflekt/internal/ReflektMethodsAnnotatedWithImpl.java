package io.github.reflekt.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Supplier;

import io.github.reflekt.ReflektAllMethods;
import io.github.reflekt.ReflektMethodsAnnotatedWith;

class ReflektMethodsAnnotatedWithImpl extends ReflektAbstractAnnotatedWith<Method> implements ReflektMethodsAnnotatedWith {

    private final Supplier<ReflektAllMethods> reflektAllMethods;

    ReflektMethodsAnnotatedWithImpl(Supplier<ReflektAllMethods> reflektAllMethods) {
        this.reflektAllMethods = reflektAllMethods;
    }

    @Override
    public Set<Method> getMethodsAnnotatedWith(Class<? extends Annotation> annotation) {
        return getAnnotatedTypes(annotation);
    }

    @Override
    protected Set<Method> getSourceDatas() {
        return reflektAllMethods.get().getAllMethods();
    }
}
