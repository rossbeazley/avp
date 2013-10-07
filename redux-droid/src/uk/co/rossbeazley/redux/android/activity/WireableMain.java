package uk.co.rossbeazley.redux.android.activity;

import uk.co.rossbeazley.redux.android.log.Logger;
import uk.co.rossbeazley.redux.eventbus.EventBus;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 22/09/13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public interface WireableMain extends WireableActivity {

    void setEventBus(EventBus eventBus);

    void setIntentParser(IntentToEventDispatcher intentParser);

    void setLogger(Logger logger);
}
