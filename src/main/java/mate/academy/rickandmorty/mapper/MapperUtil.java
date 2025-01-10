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
import mate.academy.rickandmorty.model.enums.Gender;
import mate.academy.rickandmorty.model.enums.Status;
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

    @Named("stringToStatus")
    public Status parseStatus(String status) {
        return Status.getByValue(status);
    }

    @Named("stringToGender")
    public Gender parseGender(String gender) {
        return Gender.getByValue(gender);
    }

    @Named("subDtoToLocation")
    public Location parseLocation(LocationSubDto locationSubDto) {
        if (locationSubDto.url().isBlank()) {
            return null;
        }
        String[] splittedUrl = locationSubDto.url().split("/");
        Long externalId = Long.valueOf(splittedUrl[splittedUrl.length - 1]);
        return locationService.getByExternalId(externalId);
    }

    @Named("stringsToEpisodes")
    public List<Episode> parseEpisodes(List<String> urls) {
        List<Episode> episodes = new ArrayList<>();
        for (String url : urls) {
            String[] splittedUrl = url.split("/");
            Long externalId = Long.valueOf(splittedUrl[splittedUrl.length - 1]);
            episodes.add(episodeService.getByExternalId(externalId));
        }
        return episodes;
    }
}
