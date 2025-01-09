package mate.academy.rickandmorty.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender getByValue(String value) {
        return Arrays.stream(Gender.values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Can't find gender by value: " + value));
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
