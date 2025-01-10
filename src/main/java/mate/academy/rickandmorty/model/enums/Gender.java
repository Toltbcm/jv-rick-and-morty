package mate.academy.rickandmorty.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("Female")
    FEMALE,
    @JsonProperty("Male")
    MALE,
    @JsonProperty("Genderless")
    GENDERLESS,
    @JsonProperty("unknown")
    UNKNOWN
}
