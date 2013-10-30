package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;
import uk.co.rossbeazley.avp.android.player.time.MediaPlayerTimePositionWatcher;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application extends android.app.Application {

    protected ApplicationServices services;

    public Application() {
        services = new ProductionApplicationServices(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityWiringAspect(services, services.getLogger()));

        services.executeRunnable(new Runnable() {
            @Override
            public void run() {
                createApplication();
            }
        });

    }

    private void createApplication() {

        EventBus bus = services.getBus();

        new MediaPlayerCreator(bus, services.getAndroidMediaPlayerFactory());
        new MediaPlayerPreparer(bus);
        new MediaPlayerAutoPlay(bus);
        new MediaPlayerControl(bus);
        new MediaPlayerTimePositionWatcher(new ThreadPoolFixedRateExecutor(), bus);
        services.getLogger().debug("APP CREATED");

    }

    private class ThreadPoolFixedRateExecutor implements CanExecuteCommandsAtFixedRate {

        private static final long NO_DELAY = 0;
        private final ScheduledExecutorService service;

        private ThreadPoolFixedRateExecutor() {
            this.service = Executors.newScheduledThreadPool(1);
        }

        @Override
        public void schedule(Runnable command, TimeInMilliseconds period) {
            service.scheduleAtFixedRate(command, NO_DELAY, period.value, TimeUnit.MILLISECONDS);
        }
    }
}
