package org.reflekt.internal;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.reflekt.ClassFileLocator;
import org.reflekt.ReflektAllTypes;
import org.reflekt.ReflektConf;

@SuppressWarnings("unused")
public class ReflektAllTypesImplTest {

    private ReflektConf conf = ReflektConf.builder().build();
    private ClassFileLocator mocka = mock(ClassFileLocator.class);
    private ClassFileLocator mockb = mock(ClassFileLocator.class);
    private ClassFileLocator mockc = mock(ClassFileLocator.class);
    private List<Supplier<ClassFileLocator>> locators = List.of(
            LazyBuilder.lazy(() -> mocka),
            LazyBuilder.lazy(() -> mockb),
            LazyBuilder.lazy(() -> mockc)
    );
    private final ReflektAllTypes target = new ReflektAllTypesImpl(conf, locators);

    @Test
    public void testGetAllTypes() {
        // given
        when(mocka.getClasses(anyBoolean())).thenReturn(Set.of("mockA"));
        when(mockb.getClasses(anyBoolean())).thenReturn(Set.of("mockB"));
        when(mockc.getClasses(anyBoolean())).thenReturn(Set.of("mockC"));

        // when
        var result = target.getAllTypes();

        // then
        assertThat(result, Matchers.is(Set.of("mockA", "mockB", "mockC")));
    }
}
