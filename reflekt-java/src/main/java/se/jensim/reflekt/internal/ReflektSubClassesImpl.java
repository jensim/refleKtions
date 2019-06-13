package se.jensim.reflekt.internal;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import se.jensim.reflekt.ReflektSubClasses;

class ReflektSubClassesImpl implements ReflektSubClasses {

    private final Map<String, Set<Class>> subs = new ConcurrentHashMap<>();
    private final Supplier<ReflektAllClasses> reflektAllClasses;

    ReflektSubClassesImpl(Supplier<ReflektAllClasses> reflektAllClasses) {
        this.reflektAllClasses = reflektAllClasses;
    }

    @Override
    public Set<Class> getSubClasses(Class clazz) {
        return subs.computeIfAbsent(clazz.getCanonicalName(), b -> getSubClassesOf(clazz));
    }

    private Set<Class> getSubClassesOf(Class<?> clazz) {
        return reflektAllClasses.get().getAllClasses().stream()
                .filter(c -> !clazz.equals(c))
                .filter(clazz::isAssignableFrom)
                .collect(Collectors.toSet());
    }
}
