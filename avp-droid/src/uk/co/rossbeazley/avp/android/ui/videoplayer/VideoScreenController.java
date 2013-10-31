package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.time.MediaTimePosition;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

class VideoScreenController {
    private final EventBus bus;

    VideoScreenController(final EventBus bus) {
        this.bus = bus;
    }

    void registerOnEventBus(final ControlScreen videoScreen) {

        bindPlayerPlayingEvent(videoScreen);
        bindPlayerPausedEvent(videoScreen);
        bindUserPauseEvent(videoScreen);
        bindUserPlayEvent(videoScreen);
        bindUserScrubEvent(videoScreen);
        bindTimeUpdateEvent(videoScreen);
    }

    private void bindPlayerPausedEvent(final ControlScreen videoScreen) {
        bus.whenEvent(Events.PLAYER_PAUSED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        videoScreen.showPlay();
                        videoScreen.hideBuffering();
                    }
                });
    }

    private void bindTimeUpdateEvent(final ControlScreen videoScreen) {
        bus.whenEvent(Events.MEDIA_PLAYER_TIME_UPDATE).thenRun(new FunctionWithParameter<MediaTimePosition>() {
            @Override
            public void invoke(MediaTimePosition payload) {
                videoScreen.showProgressTime(payload.getCurrentPosition());
                videoScreen.showTotalTime(payload.getTotalLength());
            }
        });
    }

    private void bindUserScrubEvent(ControlScreen videoScreen) {
        videoScreen.setScrubEventListener(new ControlScreen.CanListenForUserScrubEvents() {
            @Override
            public void userScrubbedTo(TimeInMilliseconds time) {
                bus.sendPayload(time)
                        .withEvent(Events.SCRUB);
            }
        });
    }

    private void bindUserPlayEvent(ControlScreen videoScreen) {
        videoScreen.setPlayEventListener(new ControlScreen.CanListenForUserPlayEvents() {
            @Override
            public void userPressedPlay() {
                bus.announce(Events.PLAY);
            }
        });
    }

    private void bindUserPauseEvent(ControlScreen videoScreen) {
        videoScreen.setPauseEventListener(new ControlScreen.CanListenForUserPauseEvents() {
            @Override
            public void userPressedPause() {
                bus.announce(Events.PAUSE);
            }
        });
    }

    private void bindPlayerPlayingEvent(final ControlScreen videoScreen) {
        bus.whenEvent(Events.PLAYER_PLAYING)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        videoScreen.showPause();
                        videoScreen.hideBuffering();
                    }
                });
    }
}