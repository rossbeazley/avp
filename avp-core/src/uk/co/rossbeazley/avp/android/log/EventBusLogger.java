package uk.co.rossbeazley.avp.android.log;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class EventBusLogger {

    private final Logger log;
    private final EventBus bus;

    public EventBusLogger(final Logger log, EventBus bus) {
        this.log = log;
        this.bus = bus;

        logEvent(Events.VIDEO_LOADED);
        logEvent(Events.MEDIA_PLAYER_CREATED);
        logEvent(Events.MEDIA_PLAYER_TIME_UPDATE);
        logEvent(Events.PLAYER_PAUSED);
        logEvent(Events.PLAYER_PLAYING);
        logEvent(Events.PLAYER_STOPPED);

        logEvent(Events.LOAD_VIDEO);
        logEvent(Events.PLAY);
        logEvent(Events.SCRUB);
        logEvent(Events.PAUSE);
        logEvent(Events.APP_HIDDEN);

        logEvent(Events.SHUTDOWN);
    }

    private void logEvent(final String event) {
        bus.whenEvent(event).thenRun(new FunctionWithParameter() {
            @Override
            public void invoke(Object payload) {
                log.debug(event + " : " + payload);
            }
        });
    }
}
