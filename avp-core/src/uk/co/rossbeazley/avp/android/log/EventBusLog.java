package uk.co.rossbeazley.avp.android.log;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.MediaPlaybackService;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.state.MediaPlayerStateMachine;
import uk.co.rossbeazley.avp.android.search.CurrentSearchResults;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerScreenPresenter;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class EventBusLog {

    private final Logger log;
    private final EventBus bus;

    public EventBusLog(final Logger log, EventBus bus) {
        this.log = log;
        this.bus = bus;

        logEvent(MediaPlayerPreparer.PLAYER_VIDEO_LOADED);
        logEvent(MediaPlayerCreator.PLAYER_CREATED);
        logEvent(Events.PLAYER_TIME_UPDATE);
        logEvent(MediaPlayerStateMachine.PLAYER_PAUSED);
        logEvent(MediaPlayerStateMachine.PLAYER_PLAYING);
        logEvent(MediaPlayerControl.PLAYER_STOPPED);
        logEvent(Events.PLAYER_VIEW_CREATED);

        logEvent(MediaPlaybackService.USER_LOAD_VIDEO);
        logEvent(VideoPlayerScreenPresenter.USER_PLAY);
        logEvent(VideoPlayerScreenPresenter.USER_SCRUB);
        logEvent(VideoPlayerScreenPresenter.USER_PAUSE);

        logEvent(Events.APP_HIDDEN);
        logEvent(Events.APP_SHUTDOWN);
        logEvent(Events.APP_RESUMED);
        logEvent(Events.APP_START);


        logEvent(Events.USER_WANTS_TO_GOTO_SEARCH);
        logEvent(Events.PERFORMING_QUERY);
        logEvent(CurrentSearchResults.SEARCH_RESULTS_AVAILABLE);
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
