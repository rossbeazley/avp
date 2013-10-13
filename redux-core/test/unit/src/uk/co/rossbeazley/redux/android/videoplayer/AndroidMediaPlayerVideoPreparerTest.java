package uk.co.rossbeazley.redux.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.redux.UriString;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AndroidMediaPlayerVideoPreparerTest implements MediaPlayer {


    private static final UriString ANY_URL = UriString.from("ANY");
    private String status = "NOT_PREPARED";
    private String status_prepared = "PREPARED";

    @Test
    public void createsANewMediaPlayerAndPreparesAsync() throws Exception {
        MediaPlayerFactory mpFactory = createFakeMPFactory();

        AndroidMediaPlayerVideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer(mpFactory);
        videoPreparer.playVideoUrl(ANY_URL);
        assertThat(status, is(status_prepared));
    }

    private MediaPlayerFactory createFakeMPFactory() {
        return new MediaPlayerFactory() {
            @Override
            public MediaPlayer createMediaPlayerForUri(UriString uri) {
                return AndroidMediaPlayerVideoPreparerTest.this;
            }
        };
    }

    @Override
    public void prepareAsync() {
        this.status = status_prepared;
    }
}
