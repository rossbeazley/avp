package uk.co.rossbeazley.avp.android.mediaplayer;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.log.Logger;

@RunWith(RobolectricTestRunner.class)
public class AndroidMediaPlayerFactoryTest extends MediaPlayerFactoryContract {


    @Override
    protected AndroidMediaPlayerFactory getMediaPlayerFactory() {
        return new AndroidMediaPlayerFactory(Robolectric.application, Logger.NULL);
    }
}
