package reactor.seminar1.classroom.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record VideoInfo (String id, String name, byte[] content, LocalDateTime createdAt) {
}
