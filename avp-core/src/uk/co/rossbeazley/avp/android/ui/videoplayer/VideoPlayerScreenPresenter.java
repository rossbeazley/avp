package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.state.MediaPlayerStateMachine;
import uk.co.rossbeazley.avp.android.player.time.MediaTimePosition;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class VideoPlayerScreenPresenter {
    public static final String USER_PAUSE = "pause";      //TODO rename all the VideoScreen stuff, player screen
    public static final String USER_PLAY = "play";
    public static final String USER_SCRUB = "scrub";
    public static final String USER_EXIT_VIDEO_SCREEN = "exit_video_Screen";
    private final EventBus bus;

    VideoPlayerScreenPresenter(final EventBus bus, final VideoPlayerScreen videoScreen) {
        this.bus = bus;
        registerOnEventBus(videoScreen);
    }

    private void registerOnEventBus(final VideoPlayerScreen videoScreen) {

        bindPlayerPlayingEvent(videoScreen);
        bindPlayerPausedEvent(videoScreen);
        bindUserPauseEvent(videoScreen);
        bindUserPlayEvent(videoScreen);
        bindUserScrubEvent(videoScreen);
        bindTimeUpdateEvent(videoScreen);
        bindScreenTearDownEvent(videoScreen);
    }

    private void bindPlayerPausedEvent(final VideoPlayerScreen videoScreen) {
        bus.whenEvent(MediaPlayerStateMachine.PLAYER_PAUSED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        videoScreen.showPlay();
                        videoScreen.hideBuffering();
                    }
                });
    }

    private void bindTimeUpdateEvent(final VideoPlayerScreen videoScreen) {
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

    private void bindUserScrubEvent(VideoPlayerScreen videoScreen) {
        videoScreen.setScrubEventListener(new VideoPlayerScreen.CanListenForUserScrubEvents() {
        @Override
            public void userScrubbedTo(long positionAsMillis) {
                TimeInMilliseconds positionAsMilliseconds = TimeInMilliseconds.fromLong(positionAsMillis);
                bus.sendPayload(positionAsMilliseconds) //TODO migrate to a service object?
                        .withEvent(USER_SCRUB);
            }
        });
    }

    private void bindUserPlayEvent(VideoPlayerScreen videoScreen) {
        videoScreen.setPlayEventListener(new VideoPlayerScreen.CanListenForUserPlayEvents() {
            @Override
            public void userPressedPlay() {
                bus.announce(USER_PLAY);  //TODO migrate to a service object?
            }
        });
    }

    private void bindUserPauseEvent(VideoPlayerScreen videoScreen) {
        videoScreen.setPauseEventListener(new VideoPlayerScreen.CanListenForUserPauseEvents() {
            @Override
            public void userPressedPause() {
                bus.announce(USER_PAUSE);  //TODO migrate to a service object?
            }
        });
    }

    private void bindPlayerPlayingEvent(final VideoPlayerScreen videoScreen) {
        bus.whenEvent(MediaPlayerStateMachine.PLAYER_PLAYING)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        videoScreen.showPause();
                        videoScreen.hideBuffering();
                    }
                });
    }

    private void bindScreenTearDownEvent(VideoPlayerScreen videoScreen) {
        videoScreen.setTearDownEventListener(new Screen.CanListenForScreenTearDownEvents() {
            @Override
            public void screenTearDown() {
                bus.announce(USER_EXIT_VIDEO_SCREEN); //TODO migrate to a service object? Very confused!
            }
        });
    }
}
