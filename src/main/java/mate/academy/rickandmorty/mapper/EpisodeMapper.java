package mate.academy.rickandmorty.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.EpisodeResultsDto;
import mate.academy.rickandmorty.model.Episode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {CharacterMapper.class})
public interface EpisodeMapper {

    @Mapping(source = "id", target = "externalId")
    @Mapping(source = "airDate", target = "airDate", qualifiedByName = "stringToDate")
    @Mapping(source = "created", target = "created", qualifiedByName = "stringToDateTime")
    Episode toModel(EpisodeResultsDto episodeResultsDto);

    @Named("stringToDate")
    default LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMMM d, yyyy"));
    }
}
