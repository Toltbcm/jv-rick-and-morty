package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findByNameContaining(String namePart);

    @Query("SELECT MAX(id) FROM Character")
    Long findMaxId();

    @Query("SELECT MIN(id) FROM Character")
    Long findMinId();
}
