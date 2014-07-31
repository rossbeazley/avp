package uk.co.rossbeazley.avp.android.player.creator;


public interface MediaPlayerFactory {
    Object createMediaPlayerForUri(String uri);
}
