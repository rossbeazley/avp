package uk.co.rossbeazley.avp.android.player.preparer;

import uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer;

import static uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer.PreparedState;
import static uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer.PreparedStateChangeListener;

public class AndroidMediaPlayerPreparer implements MediaPlayerPreparer {

    @Override //Could I inline this whole method into the class above?
    public void prepareMediaPlayer(final CanPrepareMediaPlayer mediaPlayer, final PreparedListener preparedListener) {
        mediaPlayer.addPreparedStateChangeListener(new PreparedStateChangeListener() {
            @Override
            public void state(PreparedState prepared) {
                preparedListener.prepared(mediaPlayer);
            }
        });
        mediaPlayer.prepareAsync();
    }

}
