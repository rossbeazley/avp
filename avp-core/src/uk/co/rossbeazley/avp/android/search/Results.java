package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.MediaItem;

import java.util.Arrays;
import java.util.List;

public final class Results {

    private final List<MediaItem> mediaItems;

    public Results(MediaItem... mediaItems) {
        this.mediaItems = Arrays.asList(mediaItems);
    }

    public Results(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    public int count() {
        return mediaItems.size();
    }

    public MediaItem result(int index) {
        return mediaItems.get(index);
    }

    public boolean empty() {
        return mediaItems == null || mediaItems.isEmpty();
    }
}
