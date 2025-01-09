package mate.academy.rickandmorty.dto.external;

public record LocationResultsDto(
        Long id,
        String name,
        String type,
        String dimension,
        String url,
        String created
) {
}
