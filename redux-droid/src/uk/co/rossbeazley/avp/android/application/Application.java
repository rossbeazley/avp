package uk.co.rossbeazley.avp.android.application;

import android.os.Handler;
import android.os.HandlerThread;
import uk.co.rossbeazley.avp.android.ReduxApplicationServices;
import uk.co.rossbeazley.avp.android.activity.IntentToEventDispatcher;
import uk.co.rossbeazley.avp.android.log.AndroidLogger;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.mediaplayer.AndroidMediaPlayerFactory;
import uk.co.rossbeazley.avp.android.videoplayer.*;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;
import uk.co.rossbeazley.avp.eventbus.executor.LooperExecutorFactory;

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
        MediaPlayerViewFactory mpViewFactory = new MediaPlayerViewFactory() {
            @Override
            public VideoView createVideoView(MediaPlayer mediaPlayer) {
                return new VideoView() {};
            }
        };
        VideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer();
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
