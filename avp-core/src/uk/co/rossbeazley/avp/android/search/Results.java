package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.MediaItem;

public class Results {

    private final MediaItem[] mediaItems;

    public Results(MediaItem... mediaItems) {
        this.mediaItems = mediaItems;
    }

    public int count() {
        return mediaItems.length;
    }

    public MediaItem result(int index) {
        return mediaItems[index];
    }

    public boolean empty() {
        return mediaItems == null || mediaItems.length == 0;
    }
}
