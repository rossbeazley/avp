package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.MediaItem;

public interface CurrentSearchResults {
    void announceState();

    void selectResult(MediaItem selected);
}
