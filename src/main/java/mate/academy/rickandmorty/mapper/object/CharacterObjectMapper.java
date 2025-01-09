package mate.academy.rickandmorty.mapper.object;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.DataMappingExceptoin;
import mate.academy.rickandmorty.mapper.object.deserializer.CharacterDeserializer;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.Location;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterObjectMapper {

    private final ObjectMapper objectMapper;

    public List<Character> mapCharacters(String data, List<Location> locations) {

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Character.class, new CharacterDeserializer(Character.class, locations));
        objectMapper.registerModule(simpleModule);
        try {
            return objectMapper.readValue(data, new TypeReference<>() {
            });
        } catch (JsonProcessingException ex) {
            throw new DataMappingExceptoin("Can't convert data to object", ex);
        }
    }
}
