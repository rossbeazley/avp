package uk.co.rossbeazley.avp.android.application;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.activity.IntentToEventDispatcher;
import uk.co.rossbeazley.avp.android.log.AndroidLogger;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.mediaplayer.AndroidMediaPlayerFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;
import uk.co.rossbeazley.avp.eventbus.executor.LooperExecutorFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class ProductionApplicationServices implements ApplicationServices {

    private EventBus bus;
    private IntentToEventDispatcher intentParser;
    private AndroidLogger logger;
    private AndroidMediaPlayerFactory androidMediaPlayerFactory;
    private Context applicationContext;
    private ScheduledExecutorService scheduledExecutorService;
    private static final int THREAD_POOL_SIZE_OF_ONE = 1;

    ProductionApplicationServices(Context applicationContext) {
        this.applicationContext = applicationContext;
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
             intentParser = new IntentToEventDispatcher(this.getBus(),this.getLogger());
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

    @Override
    public AndroidMediaPlayerFactory getAndroidMediaPlayerFactory() {
        if(androidMediaPlayerFactory==null) {
            androidMediaPlayerFactory = new AndroidMediaPlayerFactory(applicationContext, getLogger());
        }
        return androidMediaPlayerFactory;
    }

    @Override
    public void executeRunnable(final Runnable runnable) {
        final CountDownLatch latch = new CountDownLatch(1);

        Logger logger = getLogger();
        logger.debug("Creating application");

        HandlerThread thread = new HandlerThread("NOT_MAIN_THREAD");
        thread.start();
        Handler handler = new Handler(thread.getLooper());
        handler.post(new Runnable() {
            public void run() {
                runnable.run();
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

    @Override
    public ScheduledExecutorService getExecutorService() {
        if (scheduledExecutorService == null) {
            scheduledExecutorService = Executors.newScheduledThreadPool(THREAD_POOL_SIZE_OF_ONE);
        }
        return scheduledExecutorService;
    }
}
