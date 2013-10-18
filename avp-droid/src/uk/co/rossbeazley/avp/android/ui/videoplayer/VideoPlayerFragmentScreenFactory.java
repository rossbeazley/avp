package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.ui.FragmentLayoutInflater;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;

public class VideoPlayerFragmentScreenFactory implements FragmentScreenFactory {


    public VideoPlayerFragmentScreenFactory() {
    }

    @Override
    public FragmentLayoutInflater createScreenFromLayoutInflatorAndViewGroup(LayoutInflater inflater, ViewGroup container) {
        FragmentLayoutInflater screenInflater = new FragmentLayoutInflater(inflater, container);

        final VideoScreen videoScreen = new AndroidVideoScreen(screenInflater, screenInflater);
        videoScreen.bind();
        videoScreen.showBuffering();

        // hack for testing to simulate an event coming into the videoScreen
        View inflatedView = screenInflater.getInflatedView();
        inflatedView.postDelayed(new Runnable() {
            @Override
            public void run() {
                videoScreen.attachVideo(new AndroidMediaPlayerVideoOutput());
            }
        }, 5000);

        return screenInflater;
    }
}