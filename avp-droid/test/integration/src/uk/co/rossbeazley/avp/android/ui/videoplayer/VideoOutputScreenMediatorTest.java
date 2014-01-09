package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.ViewGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class VideoOutputScreenMediatorTest implements VideoOutputScreen {

    private RenderedVideoOutput videoOutput;

    @Test
    public void attachesViewPutOnEventBus() {

        RenderedVideoOutput expectedVideoOutput = new RenderedVideoOutput() { public void attachToViewGroup(ViewGroup container) {}};

        EventBus eventBus = new ExecutorEventBus();
        new VideoOutputScreenMediator(this, eventBus);
        eventBus.sendPayload(expectedVideoOutput)
                .withEvent(Events.PLAYER_VIEW_CREATED);
        assertThat(videoOutput, is(expectedVideoOutput));
    }

    @Override
    public void attachVideo(RenderedVideoOutput videoOutput) {
        this.videoOutput = videoOutput;
    }

}
