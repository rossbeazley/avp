package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.UriString;

import java.util.ArrayList;
import java.util.Collection;

import static uk.co.rossbeazley.avp.android.videoplayer.CanPrepareMediaPlayer.PreparedState;
import static uk.co.rossbeazley.avp.android.videoplayer.CanPrepareMediaPlayer.PreparedStateChangeListener;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 22/09/13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class AndroidMediaPlayerVideoPreparer implements VideoPreparer {

    private Collection<PreparedListener> preparedListeners;

    public AndroidMediaPlayerVideoPreparer() {
        preparedListeners = new ArrayList<PreparedListener>();
    }

    @Override
    public void addPreparedListener(PreparedListener preparedListener) {
        preparedListeners.add(preparedListener);
    }

    @Override
    public void prepareMediaPlayer(CanPrepareMediaPlayer mediaPlayer) {
        mediaPlayer.addPreparedStateChangeListener(new PreparedStateChangeListener() {
            @Override
            public void state(PreparedState prepared) {
                for (PreparedListener preparedListener : preparedListeners) {
                    preparedListener.prepared();
                }
            }
        });
        mediaPlayer.prepareAsync();

    }

}
