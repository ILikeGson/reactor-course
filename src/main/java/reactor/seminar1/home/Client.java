package reactor.seminar1.home;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.seminar1.home.service.WeatherForecastService;
import reactor.utils.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    public static void main(String[] args) {
        task_1();

    }

    private static void task_1() {
        WeatherForecastService forecastService = new WeatherForecastService();
        String latitude = "52.52";
        String longitude = "13.41";
        Mono.defer(() -> forecastService.canFindCityByCoordinates(latitude, longitude)
                        ? Mono.fromSupplier(() -> forecastService.getWeatherForecast(latitude, longitude))
                        : Mono.error(() -> new IllegalArgumentException("input parameters are not supported")))
                .subscribe(
                        response -> System.out.println(transformResponse(response)),
                        error -> Logger.log(error.getMessage()));
    }

    private static Mono<Boolean> applyForVisa(int age, int averageIncome, String countryName) {
        if ("Finland".equalsIgnoreCase(countryName)) {
            return Mono.error(() -> new RuntimeException("Currently not applying"));
        }

        return Mono.defer(() -> (age > 18 && averageIncome > 30_000)
                ? Mono.just(true)
                : Mono.just(false));
    }

    private static String transformResponse(String response) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("\"hourly\":\\{.*}");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            String data = matcher.group();
            String[] cutData = data.split(":\\[");
            List<String> strings = Arrays.stream(cutData)
                    .skip(1)
                    .map(splitted -> splitted.replaceAll("]|}", ""))
                    .toList();
            String[] times = strings.get(0).split(",");
            String[] temperatures = strings.get(1).split(",");
            builder.append("{\n");
            for (int i = 0; i < times.length - 1; i++) {
                builder.append("\t");
                builder.append(times[i]);
                builder.append(" : ");
                if (i >= temperatures.length) {
                    builder.append("no data");
                } else {
                    builder.append(temperatures[i]);
                }
                if (i != times.length - 2) {
                    builder.append(",\n");
                } else {
                    builder.append("\n");
                }
            }
            builder.append("}");
        }
        return builder.toString();
    }
}
