package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.EpisodeResultsDto;
import mate.academy.rickandmorty.model.Episode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {MapperUtil.class})
public interface EpisodeMapper {

    String DATE_FORMAT = "MMMM d, yyyy";

    @Mapping(target = "characters", ignore = true)
    @Mapping(source = "id", target = "externalId")
    @Mapping(source = "airDate", target = "airDate", dateFormat = DATE_FORMAT)
    @Mapping(source = "created", target = "created", qualifiedByName = "stringToDateTime")
    Episode toModel(EpisodeResultsDto episodeResultsDto);
}
