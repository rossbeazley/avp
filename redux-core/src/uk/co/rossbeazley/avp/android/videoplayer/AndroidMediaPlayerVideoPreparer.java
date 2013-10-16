package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.UriString;

import java.util.ArrayList;
import java.util.Collection;

import static uk.co.rossbeazley.avp.android.videoplayer.MediaPlayer.PreparedState;
import static uk.co.rossbeazley.avp.android.videoplayer.MediaPlayer.StateChangeListener;

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
    private Collection<VideoLoadedListener> videoLoadedListeners;

    public AndroidMediaPlayerVideoPreparer(MediaPlayerFactory mpFactory) {

        this.mpFactory = mpFactory;
        stateChangeListener = new MediaPlayerStateChangeListener();
        videoLoadedListeners = new ArrayList<VideoLoadedListener>(1);
    }

    @Override
    public void playVideoUrl(UriString url) {
        mediaplayer = mpFactory.createMediaPlayerForUri(url);
        mediaplayer.addStateChangeListener(stateChangeListener);
        mediaplayer.prepareAsync();
    }

    @Override
    public void addVideoLoadedListener(VideoLoadedListener videoLoadedListener) {
        videoLoadedListeners.add(videoLoadedListener);
    }

    private void notifyVideoLoaded() {
        VideoView videoView = mediaplayer.videoView();
        for(VideoLoadedListener videoLoadedListener : videoLoadedListeners) videoLoadedListener.videoLoaded(videoView);
    }

    private void startPlayBack() {
        mediaplayer.start();
    }


    private class MediaPlayerStateChangeListener implements StateChangeListener {

        @Override
        public void state(PreparedState prepared) {
            notifyVideoLoaded();
            startPlayBack();
        }

    }
}
