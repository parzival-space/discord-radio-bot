package space.parzival.discord.radiobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import space.parzival.discord.radiobot.properties.ClientProperties;
import space.parzival.discord.radiobot.properties.HttpProperties;
import space.parzival.discord.radiobot.properties.StreamProperties;

@SpringBootApplication
@EnableConfigurationProperties
@Import({
		ClientProperties.class,
		StreamProperties.class,
		HttpProperties.class
})
public class RadioBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadioBotApplication.class, args);
	}

}
