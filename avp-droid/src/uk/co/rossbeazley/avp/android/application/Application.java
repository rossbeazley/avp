package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.log.EventBusLog;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerStateEventDispatcher;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.render.AndroidMediaPlayerVideoOutputFactory;
import uk.co.rossbeazley.avp.android.player.render.CanCreateAndroidMediaPlayerVideoOutput;
import uk.co.rossbeazley.avp.android.player.render.MediaPlayerViewAttachement;
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
        new EventBusLog(logger,bus);
        new MediaPlayerStateEventDispatcher(bus,fixedRateExecutor);
        CanCreateAndroidMediaPlayerVideoOutput videoOutputFactory = new AndroidMediaPlayerVideoOutputFactory();
        new MediaPlayerViewAttachement(videoOutputFactory, bus);

        logger.debug("APP CREATED");

    }

}
