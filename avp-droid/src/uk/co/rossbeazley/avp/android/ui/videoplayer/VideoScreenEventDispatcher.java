package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

//TODO    think we need to get rid of this class and implement directly in the video screen
//TODO    nothing droid like in here, maybe shouldnt be in droid module?
class VideoScreenEventDispatcher {
    private final EventBus bus;

    VideoScreenEventDispatcher(final EventBus bus) {
        this.bus = bus;
    }

    void registerOnEventBus(final VideoScreen videoScreen) {

        bindPlayerPlayingEvent(videoScreen);
        bindUserPauseEvent(videoScreen);
    }

    private void bindUserPauseEvent(VideoScreen videoScreen) {
        videoScreen.setPauseEventListener(new CanListenForUserPauseEvents() {
            @Override
            public void userPressedPause() {
                bus.announce(Events.PAUSE);
            }
        });
    }

    private void bindPlayerPlayingEvent(final VideoScreen videoScreen) {
        bus.whenEvent(Events.PLAYER_PLAYING).thenRun(new Function() {
            @Override
            public void invoke() {
                videoScreen.showPause();
                videoScreen.hideBuffering();
            }
        });
    }
}