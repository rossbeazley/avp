package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.concurrent.ScheduledExecutorService;

public interface ApplicationServices {
    EventBus eventbus();

    IntentToEventDispatcher intentParser();

    Logger getLogger();

    MediaPlayerFactory getAndroidMediaPlayerFactory();

    void executeBlockingRunnableNotOnMainThread(Runnable runnable);

    ScheduledExecutorService getExecutorService();

    MediaRepositoryStub getMediaRepository();
}
