package uk.co.rossbeazley.avp.android.player.preparer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerPreparer {
    private final EventBus bus;

    public MediaPlayerPreparer(final EventBus bus) {
        this.bus = bus;

        bus.whenEvent(Events.PLAYER_CREATED).thenRun(new FunctionWithParameter<CanPrepareMediaPlayer>() {
            @Override
            public void invoke(final CanPrepareMediaPlayer payload) {
                prepareMediaPlayer(payload);
            }
        });
    }

    private void prepareMediaPlayer(final CanPrepareMediaPlayer mediaplayer) {
        CanPrepareMediaPlayer.PreparedStateChangeListener preparedStateChangeListener = createPreparedStateChangeListener(mediaplayer);
        mediaplayer.addPreparedStateChangeListener(preparedStateChangeListener);
        mediaplayer.prepareAsync();
    }

    private CanPrepareMediaPlayer.PreparedStateChangeListener createPreparedStateChangeListener(final CanPrepareMediaPlayer mediaplayer) {
        return new CanPrepareMediaPlayer.PreparedStateChangeListener() {
            @Override
            public void prepared() {
                bus.sendPayload(mediaplayer).withEvent(Events.PLAYER_VIDEO_LOADED);
            }
        };
    }
}
