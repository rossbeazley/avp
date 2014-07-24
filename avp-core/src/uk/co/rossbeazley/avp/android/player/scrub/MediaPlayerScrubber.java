package uk.co.rossbeazley.avp.android.player.scrub;

import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerScreenPresenter;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerScrubber {
    CanScrubMediaPlayer mediaPlayer;

    private ScrubbingStateMachine stateMachine;

    CanScrubMediaPlayer.ScrubCompleteListener completeListener = new CanScrubMediaPlayer.ScrubCompleteListener() {
        @Override
        public void seekComplete() {
            stateMachine.seekComplete();
        }
    };

    public MediaPlayerScrubber(EventBus bus) {
        bindVideoLoadedEvent(bus);
        bindScrubEvent(bus);
    }

    private void bindScrubEvent(EventBus bus) {
        bus.whenEvent(VideoPlayerScreenPresenter.USER_SCRUB)
                .thenRun(new FunctionWithParameter<TimeInMilliseconds>() {
                    @Override
                    public void invoke(TimeInMilliseconds payload) {
                        stateMachine.scrub(payload);
                    }
                });
    }

    private void bindVideoLoadedEvent(EventBus bus) {
        bus.whenEvent(MediaPlayerPreparer.PLAYER_VIDEO_LOADED)
                .thenRun(new FunctionWithParameter<CanScrubMediaPlayer>() {
                    @Override
                    public void invoke(CanScrubMediaPlayer payload) {
                        mediaPlayer = payload;
                        stateMachine = new ScrubbingStateMachineComposite(mediaPlayer);
                        mediaPlayer.addScrubCompleteListener(completeListener);
                    }
                });
    }





    interface ScrubbingStateMachine {
        void seekComplete();
        void scrub(TimeInMilliseconds payload);
    }

}
