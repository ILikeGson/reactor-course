package reactor.seminar2.classroom.model;

import java.time.LocalDateTime;

public record Commit(String username, String message, Integer changesCount, LocalDateTime timestamp) {
}
