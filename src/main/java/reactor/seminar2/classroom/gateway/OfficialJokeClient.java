package reactor.seminar2.classroom.gateway;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OfficialJokeClient {

    public Stream<String> getJokes() {
        return IntStream.range(0, RandomUtils.nextInt(30, 100))
                .mapToObj(i -> sendHttpRequest());
    }

    public String getJoke() {
        return sendHttpRequest();
    }

    @SneakyThrows
    private String sendHttpRequest() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://official-joke-api.appspot.com/random_joke"))
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
