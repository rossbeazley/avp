package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.player.render.MediaPlayerViewCreator;
import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public final class VideoOutputScreenPresenter {
    public VideoOutputScreenPresenter(final VideoOutputScreen videoOutputScreen, final EventBus eventBus) {
        eventBus.whenEvent(MediaPlayerViewCreator.PLAYER_VIEW_CREATED)
                .thenRun(new FunctionWithParameter<RenderedVideoOutput>() {
                    @Override
                    public void invoke(RenderedVideoOutput payload) {
                        videoOutputScreen.attachVideo(payload);
                    }
                });
    }
}
