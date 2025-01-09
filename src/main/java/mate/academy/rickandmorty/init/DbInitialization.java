package mate.academy.rickandmorty.init;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.HttpClientException;
import mate.academy.rickandmorty.mapper.object.CharacterObjectMapper;
import mate.academy.rickandmorty.mapper.object.SimpleObjectMapper;
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

    private final SimpleObjectMapper simpleObjectMapper;

    private final CharacterObjectMapper characterObjectMapper;

    private final LocationService locationService;

    private final EpisodeService episodeService;

    private final CharacterService characterService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadAndSaveCharacters() {
        List<Location> locations = getSimpleData(environment.getProperty("rickandmorty.api.location"), Location.class);
        locations = locationService.saveAll(locations);

//        List<Episode> episodes = simpleObjectMapper.mapSimpleData(fetchData(
//                api + environment.getProperty("rickandmorty.api.episode")), Episode.class);
//        episodes = episodeService.saveAll(episodes);
//
//        List<Character> characters = characterObjectMapper.mapCharacters(
//                fetchData(api + environment.getProperty("rickandmorty.api.character")),
//                locations);
//        characterService.saveAll(characters);
    }

    private <T> List<T> getSimpleData(String uri, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        do {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(uri))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(
                        httpRequest, HttpResponse.BodyHandlers.ofString());
                JsonNode jsonNode = objectMapper.readTree(response.body());
                String data = jsonNode.get("results").toString();
                result.addAll(simpleObjectMapper.mapSimpleData(data, clazz));
                uri = jsonNode.get("info").get("next").asText();
            } catch (IOException | InterruptedException ex) {
                throw new HttpClientException("Can't fetch data by URI: " + uri, ex);
            }
        }
        while (!uri.equals("null"));

        return null;
    }
}
