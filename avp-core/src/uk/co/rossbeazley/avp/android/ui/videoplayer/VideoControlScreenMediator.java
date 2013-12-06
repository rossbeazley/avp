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
                TimeInMilliseconds currentPosition = payload.getCurrentPosition();
                videoScreen.showProgressTime(currentPosition);
                TimeInMilliseconds totalLength = payload.getTotalLength();
                videoScreen.showTotalTime(totalLength);
                videoScreen.showSeekBarPosition( currentPosition.value, totalLength.value );
            }
        });
    }

    private void bindUserScrubEvent(VideoControlScreen videoScreen) {
        videoScreen.setScrubEventListener(new VideoControlScreen.CanListenForUserScrubEvents() {
        @Override
            public void userScrubbedTo(long positionAsMillis) {
                TimeInMilliseconds positionAsMilliseconds = TimeInMilliseconds.fromLong(positionAsMillis);
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