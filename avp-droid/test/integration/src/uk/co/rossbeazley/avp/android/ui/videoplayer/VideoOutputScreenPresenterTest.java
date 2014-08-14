package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.ViewGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.player.render.MediaPlayerViewCreator;
import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public final class VideoOutputScreenPresenterTest implements VideoOutputScreen {

    private RenderedVideoOutput videoOutput;

    @Test
    public void attachesViewPutOnEventBus() {

        RenderedVideoOutput expectedVideoOutput = new RenderedVideoOutput() { public void attachToViewGroup(ViewGroup container) {}};

        EventBus eventBus = new ExecutorEventBus();
        new VideoOutputScreenPresenter(this, eventBus);
        eventBus.sendPayload(expectedVideoOutput)
                .withEvent(MediaPlayerViewCreator.PLAYER_VIEW_CREATED);
        assertThat(videoOutput, is(expectedVideoOutput));
    }

    @Override
    public void attachVideo(RenderedVideoOutput videoOutput) {
        this.videoOutput = videoOutput;
    }

    @Override
    public void tearDown() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
