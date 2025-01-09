package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.LocationResultsDto;
import mate.academy.rickandmorty.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {MapperUtil.class})
public interface LocationMapper {

    @Mapping(source = "id", target = "externalId")
    @Mapping(source = "created", target = "created", qualifiedByName = "stringToDateTime")
    Location toModel(LocationResultsDto locationResultsDto);
}
