package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.ui.FragmentLayoutInflater;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class VideoPlayerFragmentScreenFactory implements FragmentScreenFactory {


    private final VideoScreenController videoScreenController;

    public VideoPlayerFragmentScreenFactory(final EventBus bus) {
        videoScreenController = new VideoScreenController(bus);
    }

    @Override
    public FragmentLayoutInflater createScreenFromLayoutInflatorAndViewGroup(LayoutInflater inflater, ViewGroup container) {
        FragmentLayoutInflater screenInflater = new FragmentLayoutInflater(inflater, container);
        final VideoControlScreen videoScreen = new AndroidVideoOutputScreenVideo(screenInflater, screenInflater);
        videoScreen.bind();
        videoScreenController.registerOnEventBus(videoScreen);
        return screenInflater;
    }
}