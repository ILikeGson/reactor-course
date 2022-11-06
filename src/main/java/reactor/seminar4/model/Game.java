package reactor.seminar4.model;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Participant white;
    private Participant black;
    private int moves;
    private AccuracyInfo accuracies;
    private Settings settings;
    private VictoryType victoryType;
    private ZonedDateTime startedAt;
    private ZonedDateTime finishedAt;
}
