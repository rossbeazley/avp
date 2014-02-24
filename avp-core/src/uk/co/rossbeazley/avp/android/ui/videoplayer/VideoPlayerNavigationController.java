package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

public class VideoPlayerNavigationController {

    private final ScreenStack screenStack;

    public VideoPlayerNavigationController(ScreenStack screenStack, EventBus bus) {
        this.screenStack = screenStack;
        bindEventListeners(bus);
    }

    private void bindEventListeners(EventBus bus) {
        bus.whenEvent(Events.USER_LOAD_VIDEO)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        showVideoPlayScreen();
                    }
                });
    }

    private void showVideoPlayScreen() {
        screenStack.pushScreen(VideoControlScreen.class);
    }

}
