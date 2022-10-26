package reactor.seminar1.classroom.service;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherForecastService {

    // https://open-meteo.com/en/docs
    @SneakyThrows
    public String getWeatherForecastInSofia() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.open-meteo.com/v1/forecast?latitude=42.7105&longitude=23.3238&hourly=temperature_2m"))
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}