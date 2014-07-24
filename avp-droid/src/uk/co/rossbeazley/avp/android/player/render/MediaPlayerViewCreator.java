package uk.co.rossbeazley.avp.android.player.render;

import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerViewCreator {


    public static final String PLAYER_VIEW_CREATED = "player_view_created";

    public MediaPlayerViewCreator(final CanCreateAndroidMediaPlayerVideoOutput canCreateAndroidMediaPlayerVideoOutput, final EventBus bus) {
        bus.whenEvent(MediaPlayerPreparer.PLAYER_VIDEO_LOADED)
                .thenRun(new FunctionWithParameter<CanAttachToAndroidView>() {
                    @Override
                    public void invoke(CanAttachToAndroidView payload) {
                        RenderedVideoOutput mediaPlayerView;
                        mediaPlayerView = canCreateAndroidMediaPlayerVideoOutput.createAndroidMediaPlayerVideoOutput(payload);
                        bus .sendPayload(mediaPlayerView)
                            .withEvent(PLAYER_VIEW_CREATED);
                    }
                });



    }
}
