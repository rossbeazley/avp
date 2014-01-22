package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.log.EventBusLog;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.render.AndroidMediaPlayerVideoOutputFactory;
import uk.co.rossbeazley.avp.android.player.render.MediaPlayerViewCreator;
import uk.co.rossbeazley.avp.android.player.scrub.MediaPlayerScrubber;
import uk.co.rossbeazley.avp.android.player.state.MediaPlayerStateEventDispatcher;
import uk.co.rossbeazley.avp.android.player.time.MediaPlayerTimePositionWatcher;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class AVPApplication {
    private final ApplicationServices services;

                              // Is this lazy?    should each service be a constructor arg
    public AVPApplication(ApplicationServices services) {
        this.services = services;
        createCoreApp();
    }

    private void createCoreApp() {
        //TODO pull this execute statement out of this class
        services.executeRunnableNotOnMainThread(new Runnable() {
            @Override
            public void run() {
                EventBus bus = services.eventbus();
                Logger logger = services.getLogger();
                ThreadPoolFixedRateExecutor fixedRateExecutor = new ThreadPoolFixedRateExecutor(services.getExecutorService());

                new MediaPlayerCreator(bus, services.getAndroidMediaPlayerFactory());
                new MediaPlayerPreparer(bus);
                new MediaPlayerAutoPlay(bus);
                new MediaPlayerControl(bus);
                new MediaPlayerTimePositionWatcher(fixedRateExecutor, bus);
                new MediaPlayerScrubber(bus);
                new MediaPlayerStateEventDispatcher(bus, fixedRateExecutor);
                new MediaPlayerViewCreator(new AndroidMediaPlayerVideoOutputFactory(), bus);

                new EventBusLog(logger, bus);
                logger.debug("APP CREATED");
            }
        });
    }
}