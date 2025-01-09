package mate.academy.rickandmorty.mapper.object;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.DataMappingExceptoin;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SimpleObjectMapper {

    private final ObjectMapper objectMapper;

    public <T> List<T> mapSimpleData(String data, Class<T> clazz) {

        try {
            return objectMapper.readValue(
                    data, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException ex) {
            throw new DataMappingExceptoin("Can't convert data to object", ex);
        }
    }
}
