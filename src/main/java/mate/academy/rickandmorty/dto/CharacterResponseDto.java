package mate.academy.rickandmorty.dto;

import mate.academy.rickandmorty.model.enums.Gender;
import mate.academy.rickandmorty.model.enums.Status;

public record CharacterResponseDto(
        Long id,
        String externalId,
        String name,
        Status status,
        Gender gender) {
}
