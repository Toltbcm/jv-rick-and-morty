package mate.academy.rickandmorty.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("Alive")
    ALIVE,
    @JsonProperty("Dead")
    DEAD,
    @JsonProperty("unknown")
    UNKNOWN
}
