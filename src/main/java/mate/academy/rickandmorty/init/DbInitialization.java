package mate.academy.rickandmorty.init;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.DataMappingExceptoin;
import mate.academy.rickandmorty.exception.HttpClientException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.Episode;
import mate.academy.rickandmorty.model.Location;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.EpisodeService;
import mate.academy.rickandmorty.service.LocationService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbInitialization {

    private final Environment environment;

    private final ObjectMapper objectMapper;

    private final LocationService locationService;

    private final EpisodeService episodeService;

    private final CharacterService characterService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadAndSaveCharacters() {
        String api = environment.getProperty("rickandmorty.api");

        List<Location> locations = mapData(fetchData(
                api + environment.getProperty("rickandmorty.api.location")), Location.class);
        locations = locationService.saveAll(locations);

        List<Episode> episodes = mapData(fetchData(
                api + environment.getProperty("rickandmorty.api.episode")), Episode.class);
        episodes = episodeService.saveAll(episodes);

        List<Character> characters = mapData(fetchData(
                api + environment.getProperty("rickandmorty.api.character")), Character.class);
        characters = characterService.saveAll(characters);
    }

    private String fetchData(String uri) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(uri))
                    .build();
            HttpResponse<String> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = objectMapper.readTree(response.body());

            return jsonNode.get("results").toString();

        } catch (IOException | InterruptedException ex) {
            throw new HttpClientException("Can't fetch data by URI: " + uri, ex);
        }
    }

    private <T> List<T> mapData(String string, Class<T> clazz) {
        try {
            return objectMapper.readValue(
                    string,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException ex) {
            throw new DataMappingExceptoin("Can't convert data to object", ex);
        }
    }
}
