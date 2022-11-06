package reactor.seminar4.model;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
public class Player {
    private String username;
    private int ratingBullet;
    private int ratingBlitz;
    private int ratingRapid;
    private int ratingClassicByFIDE;
    private String country;
    private boolean isOnline;
    private List<Game> games;
    private transient List<Player> friends;
    private ZonedDateTime registeredAt;
    private ZonedDateTime lastActivenessDate;
}