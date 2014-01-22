package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedView;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class VideoPlayerFragmentScreenFactory implements FragmentScreenFactory {


    private final EventBus bus;

    public VideoPlayerFragmentScreenFactory(final EventBus bus) {
        this.bus = bus;

    }

    @Override
    public InflatedView buildScreenFromLayoutInflatorAndViewGroup(LayoutInflater inflater, ViewGroup container) {

        InflatedView screenInflater = new InflatedView(inflater, container, R.layout.videoplayer);

        final VideoScreenViewRendererAndEventAdapter videoScreen = new VideoScreenViewRendererAndEventAdapter(screenInflater);

        new VideoControlScreenPresenter(bus, videoScreen);

        new VideoOutputScreenMediator(videoScreen, bus);

        return screenInflater;
    }
}
