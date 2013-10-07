package uk.co.rossbeazley.redux.android.mediaplayer;

import android.content.Context;
import android.net.Uri;
import uk.co.rossbeazley.redux.UriString;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 25/09/13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class AndroidMediaPlayerFactory implements MediaPlayerFactory {

    private Context applicationContext;

    public AndroidMediaPlayerFactory(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {

        AndroidMediaPlayer androidMediaPlayer = new AndroidMediaPlayer();
        try {
            androidMediaPlayer.setDataSource(applicationContext, Uri.parse(uri.uri));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
        return androidMediaPlayer;
    }
}
