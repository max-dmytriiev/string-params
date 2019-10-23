package ua.in.soul.tools.stringparams;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ParametrizedTest {

    @Test
    public void shouldInterpolateNamedParameter() {
        // GIVEN
        String pattern = "Hello, #{name}";

        // WHEN
        String result = Parametrized.of(pattern)
                .with("name", "World")
                .build();

        // THEN
        assertThat(result, is("Hello, World"));
    }

    @Test
    public void shouldInterpolateIndexedParameter() {
        // GIVEN
        String pattern = "Hello, #{}";

        // WHEN
        String result = Parametrized.of(pattern)
                .with(0, "World")
                .build();

        // THEN
        assertThat(result, is("Hello, World"));
    }

    @Test
    public void shouldUseLastProvidedValue() {
        // GIVEN
        String pattern = "Hello, #{name}";

        // WHEN
        String result = Parametrized.of(pattern)
                .with(0, "Soul")
                .with("name", "World")
                .build();

        // THEN
        assertThat(result, is("Hello, World"));
    }

    @Test
    public void shouldInterpolateMultipleParameters() {
        // GIVEN
        String pattern = "Hello, #{}! What a wonderful #{time}!";

        // WHEN
        String result = Parametrized.of(pattern)
                .with(0, "World")
                .with("time", "day")
                .build();

        // THEN
        assertThat(result, is("Hello, World! What a wonderful day!"));
    }
}