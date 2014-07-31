package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public interface CanExecuteCommandsAtFixedRate {
    public void schedule(Runnable command, TimeInMilliseconds period);
}
