package reactor.seminar4.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccuracyInfo {
    private double whiteAccuracy;
    private double blackAccuracy;
}
