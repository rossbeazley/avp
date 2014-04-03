package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.MediaItem;

public class Results {

    private final MediaItem[] mediaItems;

    public Results(MediaItem... mediaItems) {
        this.mediaItems = mediaItems;
    }

    public int size() {
        return mediaItems.length;
    }
}
