package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Episode;

public interface EpisodeService {

    List<Episode> saveAll(List<Episode> episodes);

    Episode getByExternalId(Long id);
}
