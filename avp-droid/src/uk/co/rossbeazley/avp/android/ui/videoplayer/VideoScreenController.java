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

    void registerOnEventBus(final VideoScreen videoScreen) {

        bindPlayerPlayingEvent(videoScreen);
        bindUserPauseEvent(videoScreen);
        bindUserPlayEvent(videoScreen);
        bindUserScrubEvent(videoScreen);
        bindTimeUpdateEvent(videoScreen);
    }

    private void bindTimeUpdateEvent(final VideoScreen videoScreen) {
        bus.whenEvent(Events.MEDIA_PLAYER_TIME_UPDATE).thenRun(new FunctionWithParameter<MediaTimePosition>() {
            @Override
            public void invoke(MediaTimePosition payload) {
                videoScreen.showProgressTime(payload.getCurrentPosition());
                videoScreen.showTotalTime(payload.getTotalLength());
            }
        });
    }

    private void bindUserScrubEvent(VideoScreen videoScreen) {
        videoScreen.setScrubEventListener(new VideoScreen.CanListenForUserScrubEvents() {
            @Override
            public void userScrubbedTo(TimeInMilliseconds time) {
                bus.sendPayload(time)
                        .withEvent(Events.SCRUB);
            }
        });
    }

    private void bindUserPlayEvent(VideoScreen videoScreen) {
        videoScreen.setPlayEventListener(new VideoScreen.CanListenForUserPlayEvents() {
            @Override
            public void userPressedPlay() {
                bus.announce(Events.PLAY);
            }
        });
    }

    private void bindUserPauseEvent(VideoScreen videoScreen) {
        videoScreen.setPauseEventListener(new VideoScreen.CanListenForUserPauseEvents() {
            @Override
            public void userPressedPause() {
                bus.announce(Events.PAUSE);
            }
        });
    }

    private void bindPlayerPlayingEvent(final VideoScreen videoScreen) {
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