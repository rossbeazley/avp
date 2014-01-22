package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.FragmentStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

public class VideoPlayerController {

    private final FragmentStack fragmentStack;

    public VideoPlayerController(FragmentStack fragmentStack, EventBus bus) {
        this.fragmentStack = fragmentStack;
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
        fragmentStack.pushFragment(VideoPlayerFragment.class);
    }

}
