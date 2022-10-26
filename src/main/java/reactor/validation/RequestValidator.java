package reactor.validation;

import java.util.regex.Pattern;

public class RequestValidator {
    private static final Pattern PATTERN =
            Pattern.compile("I_LOVE_JASON_STATHAM_[0-9]{5}");

    public static boolean checkUserToken(String token) {
        return PATTERN.matcher(token).matches();
    }
}
