package uk.co.rossbeazley.redux.android.videoplayer;

import uk.co.rossbeazley.redux.UriString;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 25/09/13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public interface MediaPlayerFactory {
    MediaPlayer createMediaPlayerForUri(UriString uri);
}
