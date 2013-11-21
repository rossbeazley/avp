package uk.co.rossbeazley.avp.android.mediaplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.player.render.CanAttachToAndroidView;

import static org.junit.Assert.assertTrue;

public abstract class MediaPlayerFactoryContract {

    @Test
    public void testCreateMediaPlayerFactoryCreatesSomethingThatCanControlMediaPlayer() throws Exception {
        assertTrue(getMediaPlayerFactory().createMediaPlayerForUri(ANY_URI) instanceof CanControlMediaPlayer);
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
    protected abstract AndroidMediaPlayerFactory getMediaPlayerFactory();
}
