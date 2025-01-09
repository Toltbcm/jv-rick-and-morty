package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Location;

public interface LocationService {

    List<Location> saveAll(List<Location> locations);

    Location getByExternalId(Long id);
}
