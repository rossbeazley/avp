package uk.co.rossbeazley.avp.android.player.creator;

import uk.co.rossbeazley.avp.UriString;

public interface MediaPlayerFactory {
    Object createMediaPlayerForUri(UriString uri);
}
