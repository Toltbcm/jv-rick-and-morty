package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {MapperUtil.class})
public interface CharacterMapper {

    @Mapping(source = "id", target = "externalId")
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGender")
    @Mapping(source = "origin", target = "origin", qualifiedByName = "subDtoToLocation")
    @Mapping(source = "location", target = "location", qualifiedByName = "subDtoToLocation")
    @Mapping(source = "episodes", target = "episodes", qualifiedByName = "stringsToEpisodes")
    @Mapping(source = "created", target = "created", qualifiedByName = "stringToDateTime")
    Character toModel(CharacterResultsDto characterResultsDto);
}
