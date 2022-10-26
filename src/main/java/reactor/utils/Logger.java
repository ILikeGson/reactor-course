package reactor.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Logger {


    public static void log(Object message) {
        System.out.println(message);
    }
}
