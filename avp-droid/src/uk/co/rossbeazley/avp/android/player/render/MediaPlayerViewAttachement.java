package uk.co.rossbeazley.avp.android.player.render;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

public class MediaPlayerViewAttachement {


    public MediaPlayerViewAttachement(final CanCreateAndroidMediaPlayerVideoOutput canCreateAndroidMediaPlayerVideoOutput, final EventBus bus) {
        bus.whenEvent(Events.PLAYER_VIDEO_LOADED)
                .thenRun(new FunctionWithParameter<CanAttachToAndroidView>() {
                    @Override
                    public void invoke(CanAttachToAndroidView payload) {
                        RenderedVideoOutput mediaPlayerView;
                        mediaPlayerView = canCreateAndroidMediaPlayerVideoOutput.createAndroidMediaPlayerVideoOutput(payload);
                        bus .sendPayload(mediaPlayerView)
                            .withEvent(Events.PLAYER_VIEW_CREATED);
                    }
                });



    }
}
