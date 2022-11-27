package reactor.seminar6.model;

import java.util.Set;

public record Team(Person coach, Set<Person> players) {}
