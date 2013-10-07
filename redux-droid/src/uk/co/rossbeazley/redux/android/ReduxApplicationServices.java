package uk.co.rossbeazley.redux.android;

import uk.co.rossbeazley.redux.android.activity.IntentToEventDispatcher;
import uk.co.rossbeazley.redux.android.log.Logger;
import uk.co.rossbeazley.redux.eventbus.EventBus;

public interface ReduxApplicationServices {
    EventBus getBus();

    IntentToEventDispatcher getIntentParser();

    Logger getLogger();
}
