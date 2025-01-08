package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Episode;
import mate.academy.rickandmorty.repository.EpisodeRepository;
import mate.academy.rickandmorty.service.EpisodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepository episodeRepository;

    @Transactional
    @Override
    public List<Episode> saveAll(List<Episode> episodes) {
        return episodeRepository.saveAll(episodes);
    }
}
