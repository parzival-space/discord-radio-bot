package space.parzival.discord.radiobot.properties;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "discord")
public class ClientProperties {

    /**
     * The Discord Bot Token
     */
    private String token;

    /**
     * The channel id where the bot is playing.
     */
    private String channel;
}
