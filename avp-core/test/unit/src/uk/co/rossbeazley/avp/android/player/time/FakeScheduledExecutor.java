package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

class FakeScheduledExecutor implements CanExecuteCommandsAtFixedRate{

    private Runnable taskToRun;

    @Override
    public void schedule(Runnable command, TimeInMilliseconds period) {
        taskToRun = command;
    }

    public void runOnce() {
        if (taskToRun != null) {
            taskToRun.run();
        }
    }
}
