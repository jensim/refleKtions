package io.github.reflekt.internal;

import static java.util.Collections.singleton;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.reflekt.ReflektAllMethods;
import io.github.reflekt.ReflektMethodsReturn;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

@SuppressWarnings("unused")
public class ReflektMethodsReturnImplTest {

    private ReflektAllMethods mocka = mock(ReflektAllMethods.class);
    private final ReflektMethodsReturn target = new ReflektMethodsReturnImpl(LazyBuilder.lazy(() -> mocka));

    @Test
    public void testGetMethodsReturn() throws NoSuchMethodException {
        // given
        when(mocka.getAllMethods()).thenReturn(new HashSet<>(Arrays.asList(ATestClass.class.getDeclaredMethods())));

        // when
        Set<Method> result = target.getMethodsReturn(Void.TYPE);

        // then
        assertThat(result, is(singleton(ATestClass.class.getDeclaredMethod("aTestMethod", String.class))));
    }

    private class ATestClass {

        private void aTestMethod(String param) {
            throw new UnsupportedOperationException("This is not intended for use.");
        }

        private String secondTestMethod(String asdf) {
            throw new UnsupportedOperationException("This is not intended for use.");
        }
    }
}
