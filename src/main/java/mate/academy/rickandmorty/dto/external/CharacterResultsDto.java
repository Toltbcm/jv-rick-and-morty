package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharacterResultsDto(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        LocationSubDto origin,
        LocationSubDto location,
        String image,
        @JsonProperty("episode")
        List<String> episodes,
        String url,
        String created
) {
}
