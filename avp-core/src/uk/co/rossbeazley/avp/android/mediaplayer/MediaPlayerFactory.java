package uk.co.rossbeazley.avp.android.mediaplayer;

import uk.co.rossbeazley.avp.UriString;

public interface MediaPlayerFactory {
    Object createMediaPlayerForUri(UriString uri);
}
