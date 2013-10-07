package uk.co.rossbeazley.redux.android.activity;

import android.content.Intent;
import uk.co.rossbeazley.redux.Events;
import uk.co.rossbeazley.redux.UriString;
import uk.co.rossbeazley.redux.eventbus.EventBus;

public class IntentToEventDispatcher {

    private EventBus bus;

    public IntentToEventDispatcher(EventBus bus) {
        this.bus = bus;
    }

    public void onIntent(Intent intent) {
        UriString uriString = new UriString("http://g.bbcredux.com/programme/5147705135610470298/download/25155-1380056370-333f6ae34f0224cf11176729f97ee935/20071225_004500_bbctwo_red_dwarf-lo.mp4");
        bus.sendPayload(uriString).withEvent(Events.LOAD_VIDEO);
    }
}
