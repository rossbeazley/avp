package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.time.MediaTimePosition;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

class VideoControlScreenMediator {
    private final EventBus bus;

    VideoControlScreenMediator(final EventBus bus) {
        this.bus = bus;
    }

    void registerOnEventBus(final VideoControlScreen videoScreen) {

        bindPlayerPlayingEvent(videoScreen);
        bindPlayerPausedEvent(videoScreen);
        bindUserPauseEvent(videoScreen);
        bindUserPlayEvent(videoScreen);
        bindUserScrubEvent(videoScreen);
        bindTimeUpdateEvent(videoScreen);
    }

    private void bindPlayerPausedEvent(final VideoControlScreen videoScreen) {
        bus.whenEvent(Events.PLAYER_PAUSED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        videoScreen.showPlay();
                        videoScreen.hideBuffering();
                    }
                });
    }

    private void bindTimeUpdateEvent(final VideoControlScreen videoScreen) {
        bus.whenEvent(Events.PLAYER_TIME_UPDATE).thenRun(new FunctionWithParameter<MediaTimePosition>() {
            @Override
            public void invoke(MediaTimePosition payload) {
                videoScreen.showProgressTime(payload.getCurrentPosition());
                videoScreen.showTotalTime(payload.getTotalLength());
            }
        });
    }

    private void bindUserScrubEvent(VideoControlScreen videoScreen) {
        videoScreen.setScrubEventListener(new VideoControlScreen.CanListenForUserScrubEvents() {

            long durationInMilliseconds;

            {
                bus.whenEvent(Events.PLAYER_TIME_UPDATE).thenRun(new FunctionWithParameter<MediaTimePosition>() {
                    @Override
                    public void invoke(MediaTimePosition payload) {
                        durationInMilliseconds = payload.getTotalLength().value;
                    }
                });
            }

            @Override
            public void userScrubbedTo(long positionAsPercentage) {

                long positionAsMillisecondsLong = (durationInMilliseconds * positionAsPercentage) / 100;
                TimeInMilliseconds positionAsMilliseconds = TimeInMilliseconds.fromLong(positionAsMillisecondsLong);
                bus.sendPayload(positionAsMilliseconds)
                        .withEvent(Events.USER_SCRUB);
            }
        });
    }

    private void bindUserPlayEvent(VideoControlScreen videoScreen) {
        videoScreen.setPlayEventListener(new VideoControlScreen.CanListenForUserPlayEvents() {
            @Override
            public void userPressedPlay() {
                bus.announce(Events.USER_PLAY);
            }
        });
    }

    private void bindUserPauseEvent(VideoControlScreen videoScreen) {
        videoScreen.setPauseEventListener(new VideoControlScreen.CanListenForUserPauseEvents() {
            @Override
            public void userPressedPause() {
                bus.announce(Events.USER_PAUSE);
            }
        });
    }

    private void bindPlayerPlayingEvent(final VideoControlScreen videoScreen) {
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