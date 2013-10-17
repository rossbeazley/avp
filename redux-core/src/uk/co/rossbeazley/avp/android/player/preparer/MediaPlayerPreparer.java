package uk.co.rossbeazley.avp.android.player.preparer;

import uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 25/09/13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public interface MediaPlayerPreparer {
    void addPreparedListener(PreparedListener preparedListener);

    void prepareMediaPlayer(CanPrepareMediaPlayer mediaPlayer);

    interface PreparedListener {
        void prepared();
    }
}
