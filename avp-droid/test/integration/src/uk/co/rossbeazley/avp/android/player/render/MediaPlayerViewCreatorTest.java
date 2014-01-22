package uk.co.rossbeazley.avp.android.player.render;

import android.view.SurfaceHolder;
import android.view.ViewGroup;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerViewCreatorTest implements CanCreateAndroidMediaPlayerVideoOutput {

    private RenderedVideoOutput expectedView = new RenderedVideoOutput() {
        @Override
        public void attachToViewGroup(ViewGroup container) {        }
    };

    private RenderedVideoOutput announcedView;


    @Test
    public void createsVideoOutputWhenPlayerVideoLoaded() {
        ExecutorEventBus bus = new ExecutorEventBus();

        bus.whenEvent(Events.PLAYER_VIEW_CREATED)
                .thenRun(new FunctionWithParameter<RenderedVideoOutput>() {
                    @Override
                    public void invoke(RenderedVideoOutput payload) {
                           announcedView = payload;
                    }
                });

        new MediaPlayerViewCreator(this, bus);
        CanAttachToAndroidView mediaPlayer = new CanAttachToAndroidView() {
            @Override
            public void setDisplay(SurfaceHolder surfaceHolder) {}
        };
        bus.sendPayload(mediaPlayer).withEvent(Events.PLAYER_VIDEO_LOADED);
        assertThat(announcedView, is(expectedView));
    }

    @Override
    public RenderedVideoOutput createAndroidMediaPlayerVideoOutput(CanAttachToAndroidView mediaPlayer) {
        return expectedView;
    }
}
