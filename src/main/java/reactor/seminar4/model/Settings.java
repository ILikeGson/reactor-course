package reactor.seminar4.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Settings {
    Mode mode;
    TimeControl timeControl;
}
