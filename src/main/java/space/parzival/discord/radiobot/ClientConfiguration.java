package space.parzival.discord.radiobot;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import space.parzival.discord.radiobot.properties.ClientProperties;

import java.util.List;

@Slf4j
@Component
public class ClientConfiguration {

    @Bean
    public JDA discordInstance(ClientProperties clientProperties, List<? extends ListenerAdapter> events) {
        JDA client = JDABuilder
                .createDefault(clientProperties.getToken())
                .build();

        events.forEach(client::addEventListener);

        log.debug("New Discord instance created.");
        return client;
    }
}
