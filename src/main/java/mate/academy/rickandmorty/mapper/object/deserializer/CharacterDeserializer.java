package mate.academy.rickandmorty.mapper.object.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.Location;
import mate.academy.rickandmorty.model.enums.Gender;
import mate.academy.rickandmorty.model.enums.Status;

public class CharacterDeserializer extends StdDeserializer<Character> {

    private List<Location> locations;

    public CharacterDeserializer(Class<?> vc, List<Location> locations) {
        super(vc);
        this.locations = locations;
    }

    @Override
    public Character deserialize(
            JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        Character character = new Character();
        character.setExternalId(jsonNode.get("id").asLong());
        character.setName(jsonNode.get("name").asText());
        character.setStatus(Status.getByValue(jsonNode.get("status").asText()));
        character.setGender(Gender.getByValue(jsonNode.get("gender").asText()));
        character.setSpecies(jsonNode.get("species").asText());
        character.setType(jsonNode.get("type").asText());
        character.setImage(jsonNode.get("image").asText());
        character.setUrl(jsonNode.get("url").asText());
        character.setCreated(parseDateTime(jsonNode.get("created").asText()));
        character.setLocation(
                getLocationByName(jsonNode.get("location").get("name").asText(), locations));


        return character;
    }

    private LocalDateTime parseDateTime(String string) {
        return ZonedDateTime.parse(string, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
    }

    private Location getLocationByName(String name, List<Location> locations) {
        return locations.stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Can't find location by name: " + name));
    }
}
