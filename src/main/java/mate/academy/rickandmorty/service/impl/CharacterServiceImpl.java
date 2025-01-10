package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<Character> saveAll(List<Character> characters) {
        return characterRepository.saveAll(characters);
    }

    @Override
    public CharacterResponseDto getById(Long id) {
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find character by ID: " + id));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterResponseDto> getByNamePart(String namePart) {
        return characterRepository.findByNameContaining(namePart).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public CharacterResponseDto getRandom() {
        return characterMapper.toDto(
                characterRepository.getRandom().orElseThrow(
                        () -> new EntityNotFoundException("Can't find random character")));
    }
}
