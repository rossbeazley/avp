package uk.co.rossbeazley.avp.android.player.control;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerAutoPlay {
    public MediaPlayerAutoPlay(EventBus bus) {
        loadVideoEvent(bus);
    }

    private void loadVideoEvent(final EventBus bus) {
        bus.whenEvent(Events.PLAYER_VIDEO_LOADED).thenRun(new FunctionWithParameter<CanControlMediaPlayer>() {
            @Override
            public void invoke(CanControlMediaPlayer mediaplayer) {
                mediaplayer.start();
            }
        });
    }
}
