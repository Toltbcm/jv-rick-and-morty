package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "external_id", nullable = false)
    private Long externalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "air_date", nullable = false)
    private LocalDate airDate;

    @Column(name = "episode", nullable = false)
    private String episode;

    @ManyToMany(mappedBy = "episodes")
    private List<Character> characters = new ArrayList<>();

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;
}
