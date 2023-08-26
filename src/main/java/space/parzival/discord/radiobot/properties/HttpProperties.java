package space.parzival.discord.radiobot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "http")
public class HttpProperties {

    /**
     * Useragent used to identify on webservers.
     */
    private String UserAgent = "Discord Radio Bot (https://github.com/parzival-space/discord-radio-bot)";

    private String VersionUrl = "/repos/parzival-space/discord-radio-bot/releases";
}
