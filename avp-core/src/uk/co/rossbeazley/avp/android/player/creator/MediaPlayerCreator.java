package uk.co.rossbeazley.avp.android.player.creator;

import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.player.MediaPlaybackService;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerCreator {

    public static final String PLAYER_CREATED = "media_player_created";

    public MediaPlayerCreator(final EventBus bus, final MediaPlayerFactory factory) {
        bindCreateMediaPlayerEventHandler(bus, factory);
    }

    static private void bindCreateMediaPlayerEventHandler(final EventBus bus, final MediaPlayerFactory factory) {
        bus.whenEvent(MediaPlaybackService.USER_LOAD_VIDEO).thenRun(new FunctionWithParameter<UriString>() {
            @Override
            public void invoke(final UriString payload) {
                createMediaPlayer(bus, payload,factory);
            }
        });
    }

    static private void createMediaPlayer(EventBus bus, UriString payload,MediaPlayerFactory factory) {
        Object mediaplayer = factory.createMediaPlayerForUri(payload.uri);
        bus.sendPayload(mediaplayer).withEvent(MediaPlayerCreator.PLAYER_CREATED);
    }

}
