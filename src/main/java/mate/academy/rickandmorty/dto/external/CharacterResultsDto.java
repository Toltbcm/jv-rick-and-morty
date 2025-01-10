package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import mate.academy.rickandmorty.model.enums.Gender;
import mate.academy.rickandmorty.model.enums.Status;

public record CharacterResultsDto(
        Long id,
        String name,
        Status status,
        String species,
        String type,
        Gender gender,
        LocationSubDto origin,
        LocationSubDto location,
        String image,
        @JsonProperty("episode")
        List<String> episodes,
        String url,
        String created
) {
}
