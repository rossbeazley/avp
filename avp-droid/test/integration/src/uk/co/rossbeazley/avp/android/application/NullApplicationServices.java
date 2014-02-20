package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.concurrent.ScheduledExecutorService;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 20/02/2014
* Time: 15:35
* To change this template use File | Settings | File Templates.
*/
public class NullApplicationServices implements ApplicationServices {
    @Override
    public EventBus eventbus() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IntentToEventDispatcher intentParser() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Logger getLogger() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MediaPlayerFactory getAndroidMediaPlayerFactory() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void executeRunnableNotOnMainThread(Runnable runnable) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ScheduledExecutorService getExecutorService() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
