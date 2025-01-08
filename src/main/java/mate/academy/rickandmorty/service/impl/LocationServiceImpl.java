package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Location;
import mate.academy.rickandmorty.repository.LocationRepository;
import mate.academy.rickandmorty.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Transactional
    @Override
    public List<Location> saveAll(List<Location> locations) {
        return locationRepository.saveAll(locations);
    }
}
