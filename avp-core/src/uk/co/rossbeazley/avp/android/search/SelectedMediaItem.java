package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SelectedMediaItem implements CurrentResult {
    private final EventBus bus;
    MediaItem selectResult;

    public SelectedMediaItem(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void selectResult(MediaItem selected) {
        this.selectResult = selected;       // what if we have to load from repo?
        bus.sendPayload(selected).withEvent(Search.MEDIA_ITEM_AVAILABLE);
    }
}