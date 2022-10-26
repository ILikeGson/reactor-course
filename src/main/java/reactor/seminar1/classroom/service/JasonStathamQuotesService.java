package reactor.seminar1.classroom.service;

import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class JasonStathamQuotesService {
    private static final List<String> QUOTES = List.of(
            "Я по утрам единорог, только рог в районе ног",
            "Когда комар садится тебе на яйца, понимаешь, что вопрос можно решить без применения физической силы",
            "Это рука было оторвано нахрен и прищита вот этой рукой",
            "-Где ты взял костюм?\n-Сам сшил.",
            "-Надо было терпеть до туалета\n-Но я же не терпила"
    );

    public String getFamousQuote() {
        return QUOTES.stream().skip(RandomUtils.nextInt(-1, 3)).findFirst().orElse(":(");
    }
}
