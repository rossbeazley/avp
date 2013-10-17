package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.UriString;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 25/09/13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public interface VideoPreparer {
    void addPreparedListener(PreparedListener preparedListener);

    void prepareMediaPlayer(CanPrepareMediaPlayer mediaPlayer);
}
