package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.dto.external.EpisodeResultsDto;
import mate.academy.rickandmorty.dto.external.LocationResultsDto;
import mate.academy.rickandmorty.dto.external.PageDto;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.mapper.EpisodeMapper;
import mate.academy.rickandmorty.mapper.LocationMapper;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.EpisodeService;
import mate.academy.rickandmorty.service.ExternalClientService;
import mate.academy.rickandmorty.service.LocationService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExternalClientServiceImpl implements ExternalClientService {

    private final ObjectMapper objectMapper;
    private final Environment environment;
    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final EpisodeService episodeService;
    private final EpisodeMapper episodeMapper;
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void getAndSaveAll() {
        List<LocationResultsDto> locationDtos = getAll(
                environment.getProperty("rickandmorty.api.location"),
                LocationResultsDto.class);
        locationService.saveAll(locationDtos.stream().map(locationMapper::toModel).toList());

        List<EpisodeResultsDto> episodeDtos = getAll(
                environment.getProperty("rickandmorty.api.episode"),
                EpisodeResultsDto.class);
        episodeService.saveAll(episodeDtos.stream().map(episodeMapper::toModel).toList());

        List<CharacterResultsDto> characterDtos = getAll(
                environment.getProperty("rickandmorty.api.character"),
                CharacterResultsDto.class);
        characterService.saveAll(characterDtos.stream().map(characterMapper::toModel).toList());
    }

    private <T> List<T> getAll(String uri, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        do {
            PageDto<T> pageDto = fetchPageDto(uri, clazz);
            result.addAll(pageDto.results());
            uri = pageDto.info().next();
        } while (uri != null);

        return result;
    }

    private <T> PageDto<T> fetchPageDto(String uri, Class<T> clazz) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(),
                    objectMapper.getTypeFactory().constructParametricType(PageDto.class, clazz));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new DataProcessingException("Can't fetch data by URI: " + uri, ex);
        } catch (IOException ex) {
            throw new DataProcessingException("Can't fetch data by URI: " + uri, ex);
        }
    }
}
