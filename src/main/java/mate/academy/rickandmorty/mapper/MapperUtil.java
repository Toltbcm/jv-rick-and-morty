package mate.academy.rickandmorty.mapper;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.LocationSubDto;
import mate.academy.rickandmorty.model.Episode;
import mate.academy.rickandmorty.model.Location;
import mate.academy.rickandmorty.service.EpisodeService;
import mate.academy.rickandmorty.service.LocationService;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MapperUtil {

    public static final String DATE_FORMAT = "MMMM d, yyyy";

    private final LocationService locationService;
    private final EpisodeService episodeService;

    @Named("stringToDateTime")
    public LocalDateTime parseDateTime(String dateTime) {
        return ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
    }

    @Named("subDtoToLocation")
    public Location parseLocation(LocationSubDto locationSubDto) {
        if (locationSubDto.url().isBlank()) {
            return null;
        }
        return locationService.getByExternalId(getIdFromUrl(locationSubDto.url()));
    }

    @Named("stringsToEpisodes")
    public List<Episode> parseEpisodes(List<String> urls) {
        List<Episode> episodes = new ArrayList<>();
        for (String url : urls) {
            episodes.add(episodeService.getByExternalId(getIdFromUrl(url)));
        }
        return episodes;
    }

    private Long getIdFromUrl(String url) {
        String[] splittedUrl = url.split("/");
        return Long.valueOf(splittedUrl[splittedUrl.length - 1]);
    }
}
