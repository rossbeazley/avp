package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolFixedRateExecutor implements CanExecuteCommandsAtFixedRate {

    private static final long NO_DELAY = 0;
    private final ScheduledExecutorService service;

    public ThreadPoolFixedRateExecutor(ScheduledExecutorService executorService) {
        this.service = executorService;
    }

    @Override
    public void schedule(Runnable command, TimeInMilliseconds period) {
        service.scheduleAtFixedRate(command, NO_DELAY, period.value, TimeUnit.MILLISECONDS);
    }
}
