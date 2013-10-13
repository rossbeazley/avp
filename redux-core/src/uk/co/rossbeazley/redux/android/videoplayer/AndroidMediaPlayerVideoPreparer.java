package uk.co.rossbeazley.redux.android.videoplayer;

import uk.co.rossbeazley.redux.UriString;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 22/09/13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class AndroidMediaPlayerVideoPreparer implements VideoPreparer {

    private final MediaPlayerFactory mpFactory;
    private final MediaPlayerStateChangeListener stateChangeListener;
    private MediaPlayer mediaplayer;

    public AndroidMediaPlayerVideoPreparer(MediaPlayerFactory mpFactory) {

        this.mpFactory = mpFactory;
        stateChangeListener = new MediaPlayerStateChangeListener();
    }

    @Override
    public void playVideoUrl(UriString url) {
        mediaplayer = mpFactory.createMediaPlayerForUri(url);
        mediaplayer.addStateChangeListener(stateChangeListener);
        mediaplayer.prepareAsync();
    }

    private void startPlayBack() {
        mediaplayer.start();
    }



    private class MediaPlayerStateChangeListener implements MediaPlayer.StateChangeListener {
        @Override
        public void state(MediaPlayer.PreparedState prepared) {
            startPlayBack();
        }

    }
}
