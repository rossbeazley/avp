package uk.co.rossbeazley.avp.android.mediaplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.player.control.CanControlPlaybackOfMediaPlayer;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.preparer.CanPrepareMediaPlayer;
import uk.co.rossbeazley.avp.android.player.render.CanAttachToAndroidView;
import uk.co.rossbeazley.avp.android.player.scrub.CanScrubMediaPlayer;
import uk.co.rossbeazley.avp.android.player.time.CanGetTimeFromMediaPlayer;

import static org.junit.Assert.assertTrue;

public abstract class MediaPlayerFactoryContract {

    @Test
    public void testCreateMediaPlayerFactoryCreatesSomethingThatCanControlMediaPlayer() throws Exception {
        assertTrue(getMediaPlayerFactory().createMediaPlayerForUri(ANY_URI) instanceof CanControlPlaybackOfMediaPlayer);
    }

    @Test
    public void testCreateMediaPlayerFactoryCreatesSomethingThatCanGetTimeFromMediaPlayer() throws Exception {
        assertTrue(getMediaPlayerFactory().createMediaPlayerForUri(ANY_URI) instanceof CanGetTimeFromMediaPlayer);
    }

    @Test
    public void testCreateMediaPlayerFactoryCreatesSomethingThatCanPrepareMediaPlayer() throws Exception {
        assertTrue(getMediaPlayerFactory().createMediaPlayerForUri(ANY_URI) instanceof CanPrepareMediaPlayer);
    }

    @Test
    public void testCreateMediaPlayerFactoryCreatesSomethingThatCanScrubMediaPlayer() throws Exception {
        assertTrue(getMediaPlayerFactory().createMediaPlayerForUri(ANY_URI) instanceof CanScrubMediaPlayer);
    }

    @Test
    public void testCreateMediaPlayerFactoryCreatesSomethingThatCanAttachToAndroidView() throws Exception {
        assertTrue(getMediaPlayerFactory().createMediaPlayerForUri(ANY_URI) instanceof CanAttachToAndroidView);
    }

    private static final UriString ANY_URI = UriString.from("");
    protected abstract MediaPlayerFactory getMediaPlayerFactory();
}
