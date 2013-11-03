package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.log.EventBusLogger;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerSateEventDispatcher;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.time.MediaPlayerTimePositionWatcher;
import uk.co.rossbeazley.avp.eventbus.EventBus;

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
        Logger logger = services.getLogger();
        ThreadPoolFixedRateExecutor fixedRateExecutor = new ThreadPoolFixedRateExecutor(services.getExecutorService());

        new MediaPlayerCreator(bus, services.getAndroidMediaPlayerFactory());
        new MediaPlayerPreparer(bus);
        new MediaPlayerAutoPlay(bus);
        new MediaPlayerControl(bus);
        new MediaPlayerTimePositionWatcher(fixedRateExecutor, bus);
        new EventBusLogger(logger,bus);
        new MediaPlayerSateEventDispatcher(bus,fixedRateExecutor);

        logger.debug("APP CREATED");

    }

}
