package uk.co.rossbeazley.avp.android.player.preparer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

import static uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer.prepareMediaPlayer;

public class MediaPlayerPreparerEventDispatcher {
    public MediaPlayerPreparerEventDispatcher(final EventBus bus) {

        final MediaPlayerPreparer.PreparedListener preparedListener = new MediaPlayerPreparer.PreparedListener() {
            @Override
            public void prepared(CanPrepareMediaPlayer preparedMediaPlayer) {
                bus.sendPayload(preparedMediaPlayer).withEvent(Events.VIDEO_LOADED);
            }
        };

        bus.whenEvent(Events.MEDIA_PLAYER_CREATED).thenRun(new FunctionWithParameter<CanPrepareMediaPlayer>() {
            @Override
            public void invoke(CanPrepareMediaPlayer payload) {
                prepareMediaPlayer(payload, preparedListener);
            }
        });


    }
}
