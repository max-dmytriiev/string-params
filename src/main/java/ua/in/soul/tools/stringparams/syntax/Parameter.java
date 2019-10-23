package ua.in.soul.tools.stringparams.syntax;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class Parameter {
    private final String name;
    private String value;

    public String enclosedName() {
        return "#{" + MoreObjects.firstNonNull(name, "") + "}";
    }
}
