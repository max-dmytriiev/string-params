package ua.in.soul.tools.stringparams.syntax;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterParser {
    private static final Pattern PARAM_PATTERN = Pattern.compile("#\\{([^#{}]*)}");

    public List<String> parse(String source) {
        List<String> result = Lists.newArrayList();

        Matcher matcher = PARAM_PATTERN.matcher(source);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }

        return result;
    }
}
