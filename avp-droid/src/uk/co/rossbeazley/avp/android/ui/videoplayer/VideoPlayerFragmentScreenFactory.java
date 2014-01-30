package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedLayoutView;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class VideoPlayerFragmentScreenFactory implements FragmentScreenFactory {


    private final EventBus bus;

    public VideoPlayerFragmentScreenFactory(final EventBus bus) {
        this.bus = bus;

    }

    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        final VideoScreenViewRendererAndEventAdapter result;

        result = new VideoScreenViewRendererAndEventAdapter(inflatedLayoutView);

        new VideoScreenControlsPresenter(bus, result);
        new VideoOutputScreenPresenter(result, bus);

        return result;
    }

}
