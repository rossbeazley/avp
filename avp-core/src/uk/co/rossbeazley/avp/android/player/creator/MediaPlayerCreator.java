package uk.co.rossbeazley.avp.android.player.creator;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerCreator {

    public MediaPlayerCreator(final EventBus bus, final MediaPlayerFactory factory) {
        bindCreateMediaPlayerEventHandler(bus, factory);
    }

    static private void bindCreateMediaPlayerEventHandler(final EventBus bus, final MediaPlayerFactory factory) {
        bus.whenEvent(Events.USER_LOAD_VIDEO).thenRun(new FunctionWithParameter<UriString>() {
            @Override
            public void invoke(final UriString payload) {
                createMediaPlayer(bus, payload,factory);
            }
        });
    }

    static private void createMediaPlayer(EventBus bus, UriString payload,MediaPlayerFactory factory) {
        Object mediaplayer = factory.createMediaPlayerForUri(payload);
        bus.sendPayload(mediaplayer).withEvent(Events.PLAYER_CREATED);
    }

}
