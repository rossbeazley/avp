package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.ui.FragmentLayoutInflater;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class VideoPlayerFragmentScreenFactory implements FragmentScreenFactory {


    private final EventBus bus;

    public VideoPlayerFragmentScreenFactory(final EventBus bus) {
        this.bus = bus;

    }

    @Override
    public FragmentLayoutInflater createScreenFromLayoutInflatorAndViewGroup(LayoutInflater inflater, ViewGroup container) {

        FragmentLayoutInflater screenInflater = new FragmentLayoutInflater(inflater, container);
        final VideoScreenViewRenderer videoScreen = new VideoScreenViewRenderer(screenInflater, screenInflater);
        videoScreen.bind();

        new VideoControlScreenMediator(bus).registerOnEventBus(videoScreen);

        new VideoOutputScreenMediator(videoScreen, bus);

        return screenInflater;
    }
}
