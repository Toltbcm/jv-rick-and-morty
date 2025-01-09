package mate.academy.rickandmorty.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.PageDto;
import mate.academy.rickandmorty.exception.HttpClientException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExternalClientServiceImpl {

    private final ObjectMapper objectMapper;

    public <T> List<T> getAll(String uri, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        do {
            PageDto<T> pageDto = fetchPageDto(uri, clazz);
            result.addAll(pageDto.results());
            uri = pageDto.info().next();
        } while (uri != null);

        return result;
    }

    private <T> PageDto<T> fetchPageDto(String uri, Class<T> clazz) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(),
                    objectMapper.getTypeFactory().constructParametricType(PageDto.class, clazz));
        } catch (IOException | InterruptedException ex) {
            throw new HttpClientException("Can't fetch data by URI: " + uri, ex);
        }
    }
}
