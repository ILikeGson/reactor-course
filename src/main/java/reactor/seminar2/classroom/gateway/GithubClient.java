package reactor.seminar2.classroom.gateway;

import org.apache.commons.lang3.RandomUtils;
import reactor.core.publisher.Flux;
import reactor.seminar2.classroom.model.Commit;
import reactor.utils.Generator;

import java.time.Duration;
import java.time.LocalDateTime;

public class GithubClient {
    public Flux<Commit> findCommitsInMasterByProjectName(String projectName) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> new Commit(
                        Generator.faker().funnyName().name(),
                        "initial-commit-" + i,
                        RandomUtils.nextInt(0, 100),
                        LocalDateTime.now()));
    }
}
