package uk.co.rossbeazley.avp.android.player.preparer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerPreparerEventDispatcher {
    public MediaPlayerPreparerEventDispatcher(final EventBus bus) {
        final MediaPlayerPreparer mediaPlayerPreparer = new AndroidMediaPlayerPreparer();

        mediaPlayerPreparer.addPreparedListener(new MediaPlayerPreparer.PreparedListener() {
            @Override
            public void prepared(CanPrepareMediaPlayer preparedMediaPlayer) {
                bus.sendPayload(preparedMediaPlayer).withEvent(Events.VIDEO_LOADED);
            }
        });

        bus.whenEvent(Events.MEDIA_PLAYER_CREATED).thenRun(new FunctionWithParameter<CanPrepareMediaPlayer>() {
            @Override
            public void invoke(CanPrepareMediaPlayer payload) {
                mediaPlayerPreparer.prepareMediaPlayer(payload);
            }
        });


    }
}
