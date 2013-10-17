package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class VideoPlayerCreatorEventDispatcher {

    public VideoPlayerCreatorEventDispatcher(final EventBus bus, final MediaPlayerCreator videoCreator) {
        bindMediaPlayerCreatedEventDispatcher(bus, videoCreator);
        bindCreateMediaPlayerEventHandler(bus, videoCreator);
    }

    private void bindCreateMediaPlayerEventHandler(EventBus bus, final MediaPlayerCreator videoCreator) {
        bus.whenEvent(Events.LOAD_VIDEO).thenRun(new FunctionWithParameter<UriString>() {
            @Override
            public void invoke(UriString payload) {
                videoCreator.create(payload);
            }
        });
    }

    private void bindMediaPlayerCreatedEventDispatcher(final EventBus bus, MediaPlayerCreator videoCreator) {
        videoCreator.addCreatedListener(new MediaPlayerCreator.CreatedListener() {
            @Override
            public void created(MediaPlayer mediaPlayer) {
                bus.sendPayload(mediaPlayer).withEvent(Events.MEDIA_PLAYER_CREATED);
            }
        });
    }

}
