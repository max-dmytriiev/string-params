package ua.in.soul.tools.stringparams.syntax;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class ParameterParserTest {

    private final ParameterParser sut = new ParameterParser();

    @Test
    public void shouldFindSingleParameter() {
        // GIVEN
        String source = "#{name}";

        // WHEN
        List<String> result = sut.parse(source);

        // THEN
        assertThat(result.size(), is(1));
        assertTrue(result.contains("name"));
    }

    @Test
    public void shouldFindMultipleParameters() {
        // GIVEN
        String source = "Hello, #{name}, it's a wonderful #{time}!";

        // WHEN
        List<String> result = sut.parse(source);

        // THEN
        assertThat(result.size(), is(2));
        assertTrue(result.contains("name"));
        assertTrue(result.contains("time"));
    }

    @Test
    public void shouldSkipFindMalformedParameters() {
        // GIVEN
        String source = "Hello, {name}, it's a wonderful #time}! How do you #{action about #{subject}?";

        // WHEN
        List<String> result = sut.parse(source);

        // THEN
        assertThat(result.size(), is(1));
        assertTrue(result.contains("subject"));
    }

    @Test
    public void shouldSkipInvalidParameterNames() {
        // GIVEN
        String source = "Hello, #{n{ame}, it's a wonderful #{ti#me}! How do you #{action}?";

        // WHEN
        List<String> result = sut.parse(source);

        // THEN
        assertThat(result.size(), is(1));
        assertTrue(result.contains("action"));
    }

    @Test
    public void shouldFindAnonymousParameters() {
        // GIVEN
        String source = "Hello, #{name}, it's a wonderful #{}!";

        // WHEN
        List<String> result = sut.parse(source);

        // THEN
        assertThat(result.size(), is(2));
        assertTrue(result.contains("name"));
        assertTrue(result.contains(""));
    }

}