package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/{id}")
    public CharacterResponseDto getById(@PathVariable Long id) {
        return characterService.getById(id);
    }

    @GetMapping
    public List<CharacterResponseDto> getById(@RequestParam String namePart) {
        return characterService.getByNamePart(namePart);
    }

}
