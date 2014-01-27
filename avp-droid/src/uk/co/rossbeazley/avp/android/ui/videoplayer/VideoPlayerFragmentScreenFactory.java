package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedView;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class VideoPlayerFragmentScreenFactory implements FragmentScreenFactory {


    private final EventBus bus;

    public VideoPlayerFragmentScreenFactory(final EventBus bus) {
        this.bus = bus;

    }

    @Override
    public Screen buildScreenWithInflatedView(InflatedView inflatedView) {
        return createScreenObjects(inflatedView);
    }

    private Screen createScreenObjects(InflatedView inflatedView) {
        final VideoScreenViewRendererAndEventAdapter videoScreen = new VideoScreenViewRendererAndEventAdapter(inflatedView);

        new VideoScreenControlsPresenter(bus, videoScreen);

        new VideoOutputScreenMediator(videoScreen, bus);

        return (Screen) videoScreen;
    }
}
