package org.reflekt.internal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Test;
import org.reflekt.ReflektAllMethods;
import org.reflekt.ReflektMethodsReturn;

@SuppressWarnings("unused")
public class ReflektMethodsReturnImplTest {

    private ReflektAllMethods mocka = mock(ReflektAllMethods.class);
    private final ReflektMethodsReturn target = new ReflektMethodsReturnImpl(LazyBuilder.lazy(() -> mocka));

    @Test
    public void testGetMethodsReturn() throws NoSuchMethodException {
        // given
        when(mocka.getAllMethods()).thenReturn(Set.of(ATestClass.class.getDeclaredMethods()));

        // when
        var result = target.getMethodsReturn(Void.TYPE);

        // then
        assertThat(result, is(Set.of(ATestClass.class.getDeclaredMethod("aTestMethod", String.class))));
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
