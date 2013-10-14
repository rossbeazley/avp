package uk.co.rossbeazley.redux.android.videoplayer;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.redux.Events;
import uk.co.rossbeazley.redux.UriString;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.redux.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VideoPreparerEventDispatcherTest implements VideoPreparer {

    private UriString urlLoaded = new UriString("NO URI");
    private EventBus bus;
    private VideoView videoView;
    private VideoView mpVideoView = new VideoView() {};
    private VideoLoadedListener videoLoadedListener;

    @Before
    public void createEventBus() {
        bus = new ExecutorEventBus();
    }

    @Test
    public void videoDecoderLoadsUriOnLoadVideoEvent() {
        VideoPreparerEventDispatcher decoder = new VideoPreparerEventDispatcher(bus, this);

        UriString ANY_URL = new UriString("ANY URI");

        bus.sendPayload(ANY_URL).withEvent(Events.LOAD_VIDEO);

        assertThat(urlLoaded, is(ANY_URL));
    }


    @Test
    public void whenVideoViewCreatedEventIsSentWithPayload() {
        VideoPreparerEventDispatcher decoder = new VideoPreparerEventDispatcher(bus, this);
        bus.whenEvent(Events.VIDEO_LOADED).thenRun(new FunctionWithParameter<VideoView>() {

            @Override
            public void invoke(VideoView object) {
                videoView = object;
            }
        });

        videoLoadedListener.videoLoaded(mpVideoView);

        assertThat(videoView,is(mpVideoView));
    }


    @Override
    public void playVideoUrl(UriString url) {
        urlLoaded = url;
    }

    @Override
    public void addVideoLoadedListener(VideoLoadedListener videoLoadedListener) {
        this.videoLoadedListener = videoLoadedListener;
    }
}
