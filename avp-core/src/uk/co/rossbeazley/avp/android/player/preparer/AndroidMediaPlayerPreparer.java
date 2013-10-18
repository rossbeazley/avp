package uk.co.rossbeazley.avp.android.player.preparer;

import uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer;

import java.util.ArrayList;
import java.util.Collection;

import static uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer.PreparedState;
import static uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer.PreparedStateChangeListener;

public class AndroidMediaPlayerPreparer implements MediaPlayerPreparer {

    private Collection<PreparedListener> preparedListeners;
    private CanPrepareMediaPlayer mediaPlayer;

    public AndroidMediaPlayerPreparer() {
        preparedListeners = new ArrayList<PreparedListener>();
    }

    @Override
    public void addPreparedListener(PreparedListener preparedListener) {
        preparedListeners.add(preparedListener);
    }

    @Override
    public void prepareMediaPlayer(CanPrepareMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        mediaPlayer.addPreparedStateChangeListener(createListenerToNotifyOfPreparedState());
        mediaPlayer.prepareAsync();
    }

    private PreparedStateChangeListener createListenerToNotifyOfPreparedState() {
        return new PreparedStateChangeListener() {
            @Override
            public void state(PreparedState prepared) {
                for (PreparedListener preparedListener : preparedListeners) {
                    preparedListener.prepared(mediaPlayer);
                }
            }
        };
    }

}
