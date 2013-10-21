package uk.co.rossbeazley.avp.android.application;

import android.os.Handler;
import android.os.HandlerThread;
import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.mediaplayer.AndroidMediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.creator.AndroidMediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreatorEventDispatcher;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.concurrent.CountDownLatch;

public class Application extends android.app.Application {

    protected ApplicationServices services;

    public Application() {
        services = new ProductionApplicationServices(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildApplication();
    }

    protected void buildApplication() {
        registerActivityLifecycleCallbacks(new ActivityWiringAspect(services, services.getLogger()));
        createApplicationInSecondaryThread();
    }

    private void createApplicationInSecondaryThread() {
        final CountDownLatch latch = new CountDownLatch(1);

        Logger logger = services.getLogger();
        logger.debug("Creating application");

        HandlerThread thread = new HandlerThread("NOT_MAIN_THREAD");
        thread.start();
        Handler handler = new Handler(thread.getLooper());
        handler.post(new Runnable() {
            public void run() {
                createApplication();
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Major outage when waiting for app creation to complete", e);
        }

        logger.debug("APP STARTED");
    }

    protected void createApplication() {

        EventBus bus = services.getBus();

        MediaPlayerCreator creator = new AndroidMediaPlayerCreator(services.getAndroidMediaPlayerFactory());
        new MediaPlayerCreatorEventDispatcher(bus,creator);
        new MediaPlayerPreparer(bus);
        new MediaPlayerAutoPlay(bus);
        new MediaPlayerControl(bus);

        services.getLogger().debug("APP CREATED");

    }

}
