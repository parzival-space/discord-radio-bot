package space.parzival.discord.radiobot.icecast;

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class IcyAudioPlayerManager extends DefaultAudioPlayerManager {

    public IcyAudioPlayerManager() {
        super();

        // we do not need support for YouTube, so only http sources
        this.registerSourceManager(new HttpAudioSourceManager());
    }


    public void loadIcyStream(final String url, final IcyAudioLoadResultHandler resultHandler) {
        this.loadItem(url, resultHandler);

        // todo: implement logic for current track info
    }
}
