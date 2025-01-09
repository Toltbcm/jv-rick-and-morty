package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record PageDto<T>(InfoDto info, List<T> results) {
}
