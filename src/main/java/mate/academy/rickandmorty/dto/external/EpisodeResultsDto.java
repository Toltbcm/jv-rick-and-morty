package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EpisodeResultsDto(
        Long id,
        String name,
        @JsonProperty("air_date")
        String airDate,
        String episode,
        String url,
        String created
) {
}
