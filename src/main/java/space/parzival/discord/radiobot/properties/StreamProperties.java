package space.parzival.discord.radiobot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "stream")
public class StreamProperties {

    /**
     * The url to the audio stream.
     */
    private String url = "";
}
