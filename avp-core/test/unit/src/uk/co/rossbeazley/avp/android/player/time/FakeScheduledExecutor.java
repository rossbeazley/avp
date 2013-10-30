package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

class FakeScheduledExecutor implements CanExecuteCommandsAtFixedRate{

    private Runnable taskToRun = new Runnable() {public void run() {}};

    @Override
    public void schedule(Runnable command, TimeInMilliseconds period) {
        taskToRun = command;
    }

    public void runOnce() {
        taskToRun.run();
    }
}
