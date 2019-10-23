package ua.in.soul.tools.stringparams;

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import ua.in.soul.tools.stringparams.syntax.Parameter;
import ua.in.soul.tools.stringparams.syntax.ParameterParser;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Parametrized {
    private final String pattern;
    private final List<Parameter> parameters;
    private final Map<String, Parameter> namedParameters;

    public static Parametrized of(String pattern) {
        List<Parameter> indexedParameters =
                new ParameterParser().parse(pattern).stream()
                        .map(Parameter::of).collect(Collectors.toList());

        Map<String, Parameter> namedParameters = indexedParameters.stream()
                .filter(parameter -> !Strings.isNullOrEmpty(parameter.getName()))
                .collect(Collectors.toMap(Parameter::getName, Function.identity()));

        return new Parametrized(pattern, indexedParameters, namedParameters);
    }

    public Parametrized with(String name, String value) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("You cannot refer to anonymous parameters by name. Use index instead!");
        }

        namedParameters.computeIfAbsent(name, Parameter::of).setValue(value);
        return this;
    }

    public Parametrized with(Integer index, String value) {
        if (index < 0 || index >= parameters.size()) {
            throw new IndexOutOfBoundsException(
                    String.format("There is no parameter with index [%d]; bounds are [0, %d]", index, parameters.size() - 1));
        }
        parameters.get(index).setValue(value);
        return this;
    }

    public String build() {

        String result = pattern;

        for (Parameter parameter : parameters) {

            String value = parameter.getValue();
            if (value == null) {
                throw new NoSuchElementException(String.format("No value provided for parameter [%s]", parameter.getName()));
            }
            result = result.replace(parameter.enclosedName(), value);
        }

        return result;
    }
}
