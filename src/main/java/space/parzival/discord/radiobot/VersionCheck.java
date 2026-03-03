package space.parzival.discord.radiobot;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import space.parzival.discord.radiobot.model.GitHubRelease;
import space.parzival.discord.radiobot.properties.HttpProperties;

@Slf4j
@Service
@AllArgsConstructor
public class VersionCheck {

    private HttpProperties httpProperties;
    private BuildProperties buildProperties;

    @PostConstruct
    public void check() throws Exception {
        ComparableVersion latest = this.getLatestVersion();
        ComparableVersion current = new ComparableVersion(buildProperties.getVersion());

        if (current.compareTo(latest) > 0) {
            log.warn("------------------------------------------------------------------");
            log.warn("You are currently running version {}, but {} ", current, latest);
            log.warn("is already available. Please consider updating.");
            log.warn("------------------------------------------------------------------");
        }
    }

    private ComparableVersion getLatestVersion() {
        WebClient githubClient = WebClient.builder()
            .defaultHeader("User-Agent", httpProperties.getUserAgent())
            .baseUrl("https://api.github.com")
            .build();

        Mono<GitHubRelease[]> response = githubClient
            .get()
            .uri(httpProperties.getVersionUrl())
            .retrieve()
            .bodyToMono(GitHubRelease[].class);

        GitHubRelease[] releases = response.block();
        if (releases != null && releases.length >= 1)
            return new ComparableVersion(releases[0].tag_name);

        return new ComparableVersion("0.0.0");
    }

}
