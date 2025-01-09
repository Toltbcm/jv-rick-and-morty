package mate.academy.rickandmorty.mapper;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import mate.academy.rickandmorty.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @Named("stringToDateTime")
    default LocalDateTime parseDateTime(String dateTime) {
        return ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
    }
}
