package reactor.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomUtils;
import reactor.seminar1.classroom.model.Authority;

import java.util.Arrays;

@UtilityClass
public class Generator {
    private static final Faker FAKER = new Faker();

    public static String generateFilmName() {
        return "Once in " + FAKER.country().name();
    }

    public static byte[] generateByteArray() {
        return (FAKER.file().fileName() + FAKER.file().extension()).getBytes();
    }

    public static User generateUser() {
        return new User(
                "ORG".concat(String.valueOf(RandomUtils.nextLong(99, 999))),
                FAKER.name().fullName(),
                Arrays.stream(Authority.values()).skip(RandomUtils.nextInt(0, 2)).findFirst().orElse(Authority.GUEST));
    }

    public static Faker faker() {
        return FAKER;
    }

    public record User(String id, String name, Authority authority) {
    }
}
