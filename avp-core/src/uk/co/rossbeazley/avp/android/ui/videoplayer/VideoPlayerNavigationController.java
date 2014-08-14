package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.player.MediaPlaybackService;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

public final class VideoPlayerNavigationController {

    private final ScreenStack screenStack;

    public VideoPlayerNavigationController(ScreenStack screenStack, EventBus bus) {
        this.screenStack = screenStack;
        bindEventListeners(bus);
    }

    private void bindEventListeners(EventBus bus) {
        bus.whenEvent(MediaPlaybackService.USER_LOAD_VIDEO)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        showVideoPlayScreen();
                    }
                });
    }

    private void showVideoPlayScreen() {
        screenStack.pushScreen(MediaPlayerScreen.class);
    }

}
