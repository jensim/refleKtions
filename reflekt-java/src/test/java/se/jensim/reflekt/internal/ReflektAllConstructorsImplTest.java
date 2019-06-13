package se.jensim.reflekt.internal;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static se.jensim.reflekt.internal.LazyBuilder.lazy;

import java.util.Collections;

import org.junit.Test;
import se.jensim.reflekt.ReflektAllConstructors;

public class ReflektAllConstructorsImplTest {

    private ReflektAllClasses m = mock(ReflektAllClasses.class);
    private ReflektAllConstructors target = new ReflektAllConstructorsImpl(lazy(() -> m));

    @Test
    public void testGetAllConstructors() {
        // given
        when(m.getAllClasses()).thenReturn(Collections.singleton(ATestClassToDiscover.class));

        // when
        var constructors = target.getAllConstructors();

        // then
        assertThat(constructors, hasSize(4));
    }

    @SuppressWarnings("unused")
    private class ATestClassToDiscover {

        ATestClassToDiscover(String a) {
        }

        ATestClassToDiscover(Boolean b) {
        }

        ATestClassToDiscover(Integer c) {
        }

        ATestClassToDiscover() {
        }
    }
}


