package uk.co.rossbeazley.avp.android.mediaplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public interface CanGetTimeFromMediaPlayer {
    TimeInMilliseconds getCurrentPosition();

    TimeInMilliseconds getDuration();
}
