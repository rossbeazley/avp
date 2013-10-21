package uk.co.rossbeazley.avp.android;

import uk.co.rossbeazley.avp.android.activity.IntentToEventDispatcher;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public interface ApplicationServices {
    EventBus getBus();

    IntentToEventDispatcher getIntentParser();

    Logger getLogger();

    MediaPlayerFactory getAndroidMediaPlayerFactory();
}
