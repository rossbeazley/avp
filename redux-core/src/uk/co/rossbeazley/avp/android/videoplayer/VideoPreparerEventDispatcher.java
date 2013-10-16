package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class VideoPreparerEventDispatcher {

    public VideoPreparerEventDispatcher(final EventBus bus, final VideoPreparer videoPreparer) {
        bindLoadVideoFunction(bus, videoPreparer);
        bindVideoLoadedEvent(bus, videoPreparer);

    }

    private void bindVideoLoadedEvent(final EventBus bus, VideoPreparer videoPreparer) {
        videoPreparer.addVideoLoadedListener(new VideoLoadedListener() {
            @Override
            public void videoLoaded(VideoView view) {
                bus.sendPayload(view).withEvent(Events.VIDEO_LOADED);
            }
        });
    }

    private void bindLoadVideoFunction(EventBus bus, final VideoPreparer videoPreparer) {
        bus.whenEvent("load_video").thenRun(new FunctionWithParameter<UriString>() {
            @Override
            public void invoke(UriString object) {
                videoPreparer.playVideoUrl(object);
            }
        });
    }
}
