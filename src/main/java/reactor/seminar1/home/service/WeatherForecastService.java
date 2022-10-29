package reactor.seminar1.home.service;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class WeatherForecastService {
    private static final Map<String, String> SUPPORTED_COORDINATES = Map.of("52.52", "13.41");
    private static final String OPEN_METEO_API_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&hourly=temperature_2m";

    // https://open-meteo.com/en/docs
    @SneakyThrows
    public String getWeatherForecast(String latitude, String longitude) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(OPEN_METEO_API_URL.formatted(latitude, longitude)))
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public boolean canFindCityByCoordinates(String latitude, String longitude) {
        if (!SUPPORTED_COORDINATES.containsKey(latitude)) {
            return false;
        }

        return SUPPORTED_COORDINATES.get(latitude).equals(longitude);
    }
}