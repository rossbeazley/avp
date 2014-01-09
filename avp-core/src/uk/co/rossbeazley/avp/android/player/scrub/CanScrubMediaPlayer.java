package uk.co.rossbeazley.avp.android.player.scrub;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public interface CanScrubMediaPlayer {
    void seekTo(TimeInMilliseconds time);

    void addScrubCompleteListener(ScrubCompleteListener listener);

    interface ScrubCompleteListener {
        void seekComplete();
    }
}
