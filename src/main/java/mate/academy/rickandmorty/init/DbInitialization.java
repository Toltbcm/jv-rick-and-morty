package mate.academy.rickandmorty.init;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.EpisodeResultsDto;
import mate.academy.rickandmorty.dto.external.LocationResultsDto;
import mate.academy.rickandmorty.mapper.EpisodeMapper;
import mate.academy.rickandmorty.mapper.LocationMapper;
import mate.academy.rickandmorty.service.EpisodeService;
import mate.academy.rickandmorty.service.LocationService;
import mate.academy.rickandmorty.service.external.ExternalClientServiceImpl;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbInitialization {

    private final Environment environment;
    private final ExternalClientServiceImpl clientService;
    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final EpisodeService episodeService;
    private final EpisodeMapper episodeMapper;

    @EventListener(ApplicationReadyEvent.class)
    public void loadAndSaveCharacters() {
        List<LocationResultsDto> locationDtos = clientService.getAll(
                environment.getProperty("rickandmorty.api.location"),
                LocationResultsDto.class);
        locationService.saveAll(locationDtos.stream().map(locationMapper::toModel).toList());

        List<EpisodeResultsDto> episodesDtos = clientService.getAll(
                environment.getProperty("rickandmorty.api.episode"),
                EpisodeResultsDto.class);
        episodeService.saveAll(episodesDtos.stream().map(episodeMapper::toModel).toList());
    }
}
