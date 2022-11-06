package reactor.seminar4.model;

import lombok.Getter;

@Getter
public enum MatchResult {
    WIN(1),
    LOSE(0),
    DRAW(0.5);

    private final double num;
    MatchResult(double num) {
        this.num = num;
    }
}