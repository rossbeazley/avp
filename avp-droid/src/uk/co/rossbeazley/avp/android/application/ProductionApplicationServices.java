package uk.co.rossbeazley.avp.android.application;

import android.content.Context;
import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.activity.IntentToEventDispatcher;
import uk.co.rossbeazley.avp.android.log.AndroidLogger;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.mediaplayer.AndroidMediaPlayerFactory;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;
import uk.co.rossbeazley.avp.eventbus.executor.LooperExecutorFactory;

class ProductionApplicationServices implements ApplicationServices {

    private EventBus bus;
    private IntentToEventDispatcher intentParser;
    private AndroidLogger logger;
    private AndroidMediaPlayerFactory androidMediaPlayerFactory;
    private Context applicationContext;

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
             intentParser = new IntentToEventDispatcher(this.getBus());
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
}
