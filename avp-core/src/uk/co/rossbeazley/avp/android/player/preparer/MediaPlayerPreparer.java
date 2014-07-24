package uk.co.rossbeazley.avp.android.player.preparer;

import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerPreparer {
    public static final String PLAYER_VIDEO_LOADED = "video_loaded";
    private final EventBus bus;

    public MediaPlayerPreparer(final EventBus bus) {
        this.bus = bus;

        bus.whenEvent(MediaPlayerCreator.PLAYER_CREATED).thenRun(new FunctionWithParameter<CanPrepareMediaPlayer>() {
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
                bus.sendPayload(mediaplayer).withEvent(PLAYER_VIDEO_LOADED);
            }
        };
    }
}
