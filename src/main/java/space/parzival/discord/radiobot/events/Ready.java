package space.parzival.discord.radiobot.events;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.parzival.discord.radiobot.icecast.IcyAudioLoadResultHandler;
import space.parzival.discord.radiobot.icecast.IcyAudioPlayerManager;
import space.parzival.discord.radiobot.player.AudioPlayerSendHandler;
import space.parzival.discord.radiobot.properties.ClientProperties;

import java.nio.ByteBuffer;

@Slf4j
@Component
public class Ready extends ListenerAdapter {

    @Autowired
    private IcyAudioPlayerManager playerManager;

    @Autowired
    private ClientProperties clientProperties;

    public void onReady(@NotNull ReadyEvent event) {
        log.info("This bot is now ready and connected to {} guilds.", event.getGuildTotalCount());

        JDA client = event.getJDA();
        VoiceChannel channel = client.getVoiceChannelById(clientProperties.getChannel());
        assert channel != null;
        Guild guild = channel.getGuild();
        guild.getAudioManager().openAudioConnection(channel);

        AudioPlayer player = playerManager.createPlayer();
        guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));

        playerManager.loadIcyStream("https://play.sas-media.ru/play_256", new IcyAudioLoadResultHandler() {
            @Override
            public void metadataUpdated(String metadata) {

            }

            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                log.info("Playing {} by {}", audioTrack.getInfo().title, audioTrack.getInfo().author);
                player.playTrack(audioTrack);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {

            }

            @Override
            public void noMatches() {
                log.error("No matches.");
            }

            @Override
            public void loadFailed(FriendlyException e) {
                log.error("Load failed:", e);
            }
        });
    }
}
