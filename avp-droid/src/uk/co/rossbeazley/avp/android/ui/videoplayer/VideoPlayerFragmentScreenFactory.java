package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.ui.FragmentLayoutInflater;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class VideoPlayerFragmentScreenFactory implements FragmentScreenFactory {


    private final VideoScreenEventDispatcher videoScreenEventDispatcher;

    public VideoPlayerFragmentScreenFactory(final EventBus bus) {
        videoScreenEventDispatcher = new VideoScreenEventDispatcher(bus);
    }

    @Override
    public FragmentLayoutInflater createScreenFromLayoutInflatorAndViewGroup(LayoutInflater inflater, ViewGroup container) {
        FragmentLayoutInflater screenInflater = new FragmentLayoutInflater(inflater, container);
        final VideoScreen videoScreen = new AndroidVideoScreen(screenInflater, screenInflater);
        videoScreen.bind();
        videoScreenEventDispatcher.registerOnEventBus(videoScreen);
        return screenInflater;
    }
}