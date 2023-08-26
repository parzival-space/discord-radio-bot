package space.parzival.discord.radiobot.icecast;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;

public interface IcyAudioLoadResultHandler extends AudioLoadResultHandler {

    void metadataUpdated(String metadata);
}
