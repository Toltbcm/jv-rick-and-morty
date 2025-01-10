package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoint for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Operation(summary = "Get character by ID", description = "Get character by ID")
    @GetMapping("/{id}")
    public CharacterResponseDto getById(@PathVariable Long id) {
        return characterService.getById(id);
    }

    @Operation(summary = "Get character by ID", description = "Get character by ID")
    @GetMapping("/random")
    public CharacterResponseDto getRandom() {
        return characterService.getRandom();
    }

    @Operation(summary = "Get characters by name part", description = "Get character by name part")
    @GetMapping
    public List<CharacterResponseDto> getById(@RequestParam String namePart) {
        return characterService.getByNamePart(namePart);
    }
}
