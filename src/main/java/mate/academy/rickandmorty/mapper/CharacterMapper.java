package mate.academy.rickandmorty.mapper;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.Location;
import mate.academy.rickandmorty.model.enums.Gender;
import mate.academy.rickandmorty.model.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    // TODO
    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "episodes", ignore = true)
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGender")
    @Mapping(source = "location", target = "location", qualifiedByName = "subDtoToLocation")
    @Mapping(source = "created", target = "created", qualifiedByName = "stringToDateTime")
    Character toModel(CharacterResultsDto characterResultsDto);

    @Named("stringToDateTime")
    default LocalDateTime parseDateTime(String dateTime) {
        return ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
    }

    @Named("stringToStatus")
    default Status parseStatus(String status) {
        return Status.getByValue(status);
    }

    @Named("stringToGender")
    default Gender parseGender(String gender) {
        return Gender.getByValue(gender);
    }

    @Named("subDtoToLocation")
    default Location parseLocation() {
        Location location = new Location();
        location.setId(11L);
        return location;
    }
}
