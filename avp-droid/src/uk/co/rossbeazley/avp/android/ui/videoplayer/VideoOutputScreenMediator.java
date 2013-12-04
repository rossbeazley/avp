package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class VideoOutputScreenMediator {
    public VideoOutputScreenMediator(final VideoOutputScreen videoOutputScreen, final EventBus eventBus) {
        eventBus.whenEvent(Events.PLAYER_VIEW_CREATED)
                .thenRun(new FunctionWithParameter<RenderedVideoOutput>() {
                    @Override
                    public void invoke(RenderedVideoOutput payload) {
                        videoOutputScreen.attachVideo(payload);
                    }
                });
    }
}
