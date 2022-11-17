package reactor.seminar5.model;

import lombok.Builder;

@Builder
public class Review {
    private String clientName;
    private String message;
    private int rating;
}
