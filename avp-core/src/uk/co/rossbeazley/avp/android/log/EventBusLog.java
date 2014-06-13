package uk.co.rossbeazley.avp.android.log;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class EventBusLog {

    private final Logger log;
    private final EventBus bus;

    public EventBusLog(final Logger log, EventBus bus) {
        this.log = log;
        this.bus = bus;

        logEvent(Events.PLAYER_VIDEO_LOADED);
        logEvent(Events.PLAYER_CREATED);
        logEvent(Events.PLAYER_TIME_UPDATE);
        logEvent(Events.PLAYER_PAUSED);
        logEvent(Events.PLAYER_PLAYING);
        logEvent(Events.PLAYER_STOPPED);
        logEvent(Events.PLAYER_VIEW_CREATED);

        logEvent(Events.USER_LOAD_VIDEO);
        logEvent(Events.USER_PLAY);
        logEvent(Events.USER_SCRUB);
        logEvent(Events.USER_PAUSE);

        logEvent(Events.APP_HIDDEN);
        logEvent(Events.APP_SHUTDOWN);

        logEvent(Events.USER_WANTS_TO_GOTO_SEARCH);
        logEvent(Events.PERFORMING_QUERY);
        logEvent(Events.SEARCH_RESULTS_AVAILABLE);
    }

    private void logEvent(final String event) {
        bus.whenEvent(event).thenRun(new FunctionWithParameter() {
            @Override
            public void invoke(Object payload) {
                log.debug("___EVENT: " + event + " : " + payload);
            }
        });
    }
}
