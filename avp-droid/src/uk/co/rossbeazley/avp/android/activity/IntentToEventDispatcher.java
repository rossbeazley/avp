package uk.co.rossbeazley.avp.android.activity;

import android.content.Intent;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class IntentToEventDispatcher {

    private EventBus bus;

    public IntentToEventDispatcher(EventBus bus) {
        this.bus = bus;
    }

    public void onIntent(Intent intent) {
        UriString uriString = new UriString("http://g.bbcredux.com/programme/5148442151998464700/download/25155-1382302817-169fea1ba7c0ec48df9263e10c3975e8/20071227_002500_bbctwo_red_dwarf-lo.mp4");
        bus.sendPayload(uriString).withEvent(Events.LOAD_VIDEO);
    }
}
