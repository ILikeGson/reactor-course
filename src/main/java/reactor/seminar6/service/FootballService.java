package reactor.seminar6.service;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.seminar6.model.Person;
import reactor.seminar6.model.Team;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class FootballService {
    private static final Team FC_BARCELONA = initFcb();
    private static final Team FC_REAL_MADRID = initRm();

    public Flux<String> broadcast() {
        return Flux.<String>create(emitter -> {
            int fcbScores = 0;
            int rmScores = 0;
            for (int i = 1; i <= 90 && !emitter.isCancelled(); i++) {
                if (i % new Random().nextInt(1, 22) == 6) {
                    fcbScores++;
                } else if (i % new Random().nextInt(1, 10) == 7) {
                    rmScores++;
                }
                emitter.next("%d min: FCB - RM %d:%d".formatted(i, fcbScores, rmScores));
            }
            emitter.complete();
        }).delayElements(Duration.ofMillis(100));
    }

    private static Team initFcb() {
        return new Team(new Person("Xavier Hernández Creus", 42),
                Set.of(new Person("Robert Lewandowski", 34),
                        new Person("Raphael Dias Belloli", 25),
                        new Person("Anssumane Fati", 20),
                        new Person("Frenkie de Jong", 25),
                        new Person("Sergio Busquets Burgos", 34),
                        new Person("Chadi Riad Dnanou", 19),
                        new Person("Jordi Alba Ramos", 33),
                        new Person("Marcos Alonso Mendoza", 31),
                        new Person("Pablo Martín Páez Gavira", 18),
                        new Person("Alejandro Balde Martínez", 19),
                        new Person("Marc-Andre ter Stegen", 30)));
    }

    private static Team initRm() {
        return new Team(new Person(" Carlo Ancelotti", 63),
                Set.of(new Person("Daniel Ceballos Fernandez", 26),
                        new Person("Federico Santiago Valverde Dipetta", 24),
                        new Person("Vinícius José Paixão de Oliveira Júnior", 22),
                        new Person("Eduardo Camavinga", 20),
                        new Person("Aurélien Tchouaméni", 22),
                        new Person("Toni Kroos", 32),
                        new Person("José Ignacio Fernández Iglesias", 32),
                        new Person("Éder Gabriel Militão", 24),
                        new Person("David Alaba", 30),
                        new Person("Ferland Mendy", 27),
                        new Person("Thibaut Courtois", 30)));
    }
}
