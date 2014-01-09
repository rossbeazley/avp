package uk.co.rossbeazley.avp.android.player.scrub;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

class ScrubbingStateMachineComposite implements MediaPlayerScrubber.ScrubbingStateMachine {

    private final CanScrubMediaPlayer mediaPlayer;
    private MediaPlayerScrubber.ScrubbingStateMachine currentState;
    private MediaPlayerScrubber.ScrubbingStateMachine seekingState;
    private MediaPlayerScrubber.ScrubbingStateMachine idleState;
    private MediaPlayerScrubber.ScrubbingStateMachine pendingState;

    public ScrubbingStateMachineComposite(CanScrubMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        currentState = idleState = new IdleState();
        seekingState = new SeekingState();
        pendingState = null;
    }

    @Override
    public void seekComplete() {
        currentState.seekComplete();
    }

    @Override
    public void scrub(TimeInMilliseconds payload) {
        currentState.scrub(payload);
    }



    private class IdleState implements MediaPlayerScrubber.ScrubbingStateMachine {
        public void seekComplete() { }
        public void scrub(TimeInMilliseconds payload) {
            currentState = seekingState;
            mediaPlayer.seekTo(payload);
        }
    }

    private class SeekingState implements MediaPlayerScrubber.ScrubbingStateMachine {
        public void seekComplete() {
            currentState = idleState;
        }
        public void scrub(TimeInMilliseconds payload) {
            currentState = new PendingState(payload);
        }
    }


    private class PendingState implements MediaPlayerScrubber.ScrubbingStateMachine {
        private TimeInMilliseconds pendingPayload;

        public PendingState(TimeInMilliseconds pendingPayload) {
            this.pendingPayload = pendingPayload;
        }

        public void seekComplete() {
            currentState = seekingState;
            mediaPlayer.seekTo(pendingPayload);
        }

        public void scrub(TimeInMilliseconds payload) {
            this.pendingPayload = payload;
        }
    }

}
