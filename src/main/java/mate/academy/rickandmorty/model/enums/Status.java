package mate.academy.rickandmorty.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum Status {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("unknown");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public static Status getByValue(String value) {
        return Arrays.stream(Status.values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Can't find status by value: " + value));
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
