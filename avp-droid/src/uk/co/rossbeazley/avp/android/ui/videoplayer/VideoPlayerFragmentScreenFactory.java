package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.player.render.AndroidMediaPlayerVideoOutputFactory;
import uk.co.rossbeazley.avp.android.player.render.MediaPlayerViewCreator;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public final class VideoPlayerFragmentScreenFactory implements FragmentScreenFactory {


    private final EventBus bus;

    public VideoPlayerFragmentScreenFactory(final EventBus bus) {
        this.bus = bus;

    }

    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        final VideoOutputScreen result;

        final VideoScreenAndroidView controlsView = new VideoScreenAndroidView(inflatedLayoutView);
        result = new PlayerScreenAndroidView(inflatedLayoutView);

        new VideoPlayerScreenPresenter(bus, controlsView);
        new VideoOutputScreenPresenter(result, bus);    // think this dual presenter is a bit of a special
        //This isnt in the core module, its only in droid
        new MediaPlayerViewCreator(new AndroidMediaPlayerVideoOutputFactory(), bus); //TODO work out how to get rid of this

        return new MediaPlayerScreen() {

            @Override
            public void tearDown() {
                result.tearDown();
                controlsView.tearDown();
            }

            @Override
            public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) { }
        };
    }

}
