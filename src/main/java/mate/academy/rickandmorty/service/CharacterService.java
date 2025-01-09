package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {

    List<Character> saveAll(List<Character> characters);

    CharacterResponseDto getById(Long id);

    List<CharacterResponseDto> getByNamePart(String namePart);
}
