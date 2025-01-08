package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.model.enums.Gender;
import mate.academy.rickandmorty.model.enums.Status;

@Data
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Status status;

    @Column(name = "species")
    private String species;

    @Column(name = "type")
    private String type;

    @Column(name = "gender")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "origin_location_id")
    private Location origin;

    @ManyToOne
    @JoinColumn(name = "last_location_id")
    private Location location;

    private String image;

    @ManyToMany
    @JoinTable(
            name = "character_episode",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    private List<Episode> episodes;

    @Column(name = "url")
    private String url;

    @Column(name = "created")
    private LocalDateTime created;
}
