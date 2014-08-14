package uk.co.rossbeazley.avp.android.player;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public final class CanPlayMediaTest {

    private UriString uristring;
    private final String uri = "http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4";
    private UriString expectedUriString = UriString.from(uri);


    @Test
    public void testQuery() throws Exception {
         EventBus bus = new ExecutorEventBus();

        bus.whenEvent(MediaPlaybackService.USER_LOAD_VIDEO).thenRun(new FunctionWithParameter<UriString>() {

            @Override
            public void invoke(UriString payload) {
                uristring = payload;
            }
        });

        CanPlayMedia canPlayMedia = new MediaPlaybackService(bus);

        canPlayMedia.play(uri);
        assertThat(uristring, is(expectedUriString));
    }

}
