package uk.co.rossbeazley.redux.android.application;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import uk.co.rossbeazley.redux.android.ReduxApplicationServices;
import uk.co.rossbeazley.redux.android.activity.IntentToEventDispatcher;
import uk.co.rossbeazley.redux.android.log.AndroidLogger;
import uk.co.rossbeazley.redux.android.log.Logger;
import uk.co.rossbeazley.redux.android.videoplayer.AndroidMediaPlayerVideoPreparer;
import uk.co.rossbeazley.redux.android.videoplayer.VideoPreparer;
import uk.co.rossbeazley.redux.android.videoplayer.VideoPreparerEventDispatcher;
import uk.co.rossbeazley.redux.eventbus.EventBus;

public class ProductionApplicationServices extends Application implements ReduxApplicationServices {

    private EventBus bus;
    private IntentToEventDispatcher intentParser;
    private AndroidLogger logger;


    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityWiringAspect(this));


        createApplicationSecondaryThread();
    }

    private void createApplicationServices() {
        VideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer();
        VideoPreparerEventDispatcher videoPreparerEventDispatcher = new VideoPreparerEventDispatcher(getBus(), videoPreparer);
    }

    private void createApplicationSecondaryThread() {

        HandlerThread thread = new HandlerThread("NONE_UI_THREAD");
        thread.start();
        Handler handler = new Handler(thread.getLooper());
        handler.post(new Runnable() {
            public void run() {
                //construct application core
                createApplicationServices();
            }
        });
    }

    @Override
    public EventBus getBus() {
        if(bus==null) {
            bus = uk.co.rossbeazley.redux.eventbus.EventBusFactory.createEventBus();
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
