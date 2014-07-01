package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.MediaItem;

public interface CurrentResult {

    String MEDIA_ITEM_AVAILABLE = "media_item_available";
    String MEDIA_ITEM_SELECTING = "media_item_selecting";

    void selectResult(MediaItem selected);
}
