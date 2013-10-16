package uk.co.rossbeazley.redux.android.application;

import android.os.Handler;
import android.os.HandlerThread;
import uk.co.rossbeazley.redux.android.ReduxApplicationServices;
import uk.co.rossbeazley.redux.android.activity.IntentToEventDispatcher;
import uk.co.rossbeazley.redux.android.log.AndroidLogger;
import uk.co.rossbeazley.redux.android.log.Logger;
import uk.co.rossbeazley.redux.android.mediaplayer.AndroidMediaPlayerFactory;
import uk.co.rossbeazley.redux.android.videoplayer.AndroidMediaPlayerVideoPreparer;
import uk.co.rossbeazley.redux.android.videoplayer.VideoPreparer;
import uk.co.rossbeazley.redux.android.videoplayer.VideoPreparerEventDispatcher;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.executor.ExecutorEventBus;
import uk.co.rossbeazley.redux.eventbus.executor.LooperExecutorFactory;

public class Application extends android.app.Application implements ReduxApplicationServices {

    private EventBus bus;
    private IntentToEventDispatcher intentParser;
    private AndroidLogger logger;


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

        HandlerThread thread = new HandlerThread("NONE_UI_THREAD") {
            {
                Handler handler = new Handler(getLooper());
                handler.post(new Runnable() {
                    public void run() {

                        createApplication();
                    }
                });
            }
        };
        thread.start();

    }

    protected void createApplication() {
        AndroidMediaPlayerFactory mpFactory = new AndroidMediaPlayerFactory(this);
        VideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer(mpFactory, );
        VideoPreparerEventDispatcher videoPreparerEventDispatcher = new VideoPreparerEventDispatcher(getBus(), videoPreparer);


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
