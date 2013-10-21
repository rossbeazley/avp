package uk.co.rossbeazley.avp.android.application;

import android.os.Handler;
import android.os.HandlerThread;
import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.activity.IntentToEventDispatcher;
import uk.co.rossbeazley.avp.android.log.AndroidLogger;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.mediaplayer.AndroidMediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.creator.AndroidMediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreatorEventDispatcher;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;
import uk.co.rossbeazley.avp.eventbus.executor.LooperExecutorFactory;

import java.util.concurrent.CountDownLatch;

public class Application extends android.app.Application implements ApplicationServices {

    private AndroidMediaPlayerFactory androidMediaPlayerFactory;
    private EventBus bus;
    private IntentToEventDispatcher intentParser;
    private AndroidLogger logger;
    private CountDownLatch latch;


    @Override
    public void onCreate() {
        super.onCreate();

        buildApplication();
    }

    protected void buildApplication() {
        registerActivityLifecycleCallbacks(new ActivityWiringAspect(this, getLogger()));
        createApplicationInSecondaryThread();
    }

    private void createApplicationInSecondaryThread() {
        getLogger().debug("Creating application");
        HandlerThread thread = new HandlerThread("NOT_MAIN_THREAD") {

        };
        thread.start();

        latch = new CountDownLatch(1);

        Handler handler = new Handler(thread.getLooper());
        handler.post(new Runnable() {
            public void run() {

                createApplication();
                latch.countDown();
            }
        });

        //wait for app creation
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Major outage when waiting for app creation to complete", e);
        }

        getLogger().debug("APP STARTED");
    }

    protected void createApplication() {

        MediaPlayerCreator creator = new AndroidMediaPlayerCreator(getAndroidMediaPlayerFactory());
        new MediaPlayerCreatorEventDispatcher(getBus(),creator);

        new MediaPlayerPreparer(getBus());
        new MediaPlayerAutoPlay(getBus());
        new MediaPlayerControl(getBus());

        getLogger().debug("APP CREATED");

    }

    private AndroidMediaPlayerFactory getAndroidMediaPlayerFactory() {
        if(androidMediaPlayerFactory==null) {
            androidMediaPlayerFactory = new AndroidMediaPlayerFactory(this, getLogger());
        }
        return androidMediaPlayerFactory;
    }

    @Override
    public EventBus getBus() {
        if(bus==null) {
            bus = new ExecutorEventBus(new LooperExecutorFactory() );
        }
        return bus;
    }

    @Override
    public IntentToEventDispatcher getIntentParser() {
         if(intentParser==null) {
             intentParser = new IntentToEventDispatcher(getBus());
         }

        return intentParser;
    }

    @Override
    public Logger getLogger() {
        if(logger ==null) {
            logger = new AndroidLogger();
        }
        return logger;
    }
}
