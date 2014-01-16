package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.ui.FragmentStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class VideoPlayerController {

    private final FragmentStack fragmentStack;

    public VideoPlayerController(FragmentStack fragmentStack, EventBus bus) {
        this.fragmentStack = fragmentStack;
    }

    private void showVideoPlayScreen() {
        fragmentStack.pushFragment(new VideoPlayerFragment());
    }

}
