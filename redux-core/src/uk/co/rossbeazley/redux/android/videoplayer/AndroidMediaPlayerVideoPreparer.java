package uk.co.rossbeazley.redux.android.videoplayer;

import uk.co.rossbeazley.redux.UriString;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 22/09/13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class AndroidMediaPlayerVideoPreparer implements VideoPreparer {

    private final MediaPlayerFactory mpFactory;

    public AndroidMediaPlayerVideoPreparer(MediaPlayerFactory mpFactory) {

        this.mpFactory = mpFactory;
    }

    @Override
    public void loadVideoUrl(UriString url) {

    }
}
