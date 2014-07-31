package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public interface CanGetTimeFromMediaPlayer {
    TimeInMilliseconds getCurrentPosition();

    TimeInMilliseconds getDuration();
}
