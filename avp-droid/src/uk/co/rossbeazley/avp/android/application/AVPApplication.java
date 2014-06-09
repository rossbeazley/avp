package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.log.EventBusLog;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.concurrent.ScheduledExecutorService;

public class AVPApplication {

    public AVPApplication(final ApplicationServices services) {

        EventBus bus = services.eventbus();
        Logger logger = services.getLogger();
        MediaPlayerFactory androidMediaPlayerFactory = services.getAndroidMediaPlayerFactory();
        ScheduledExecutorService executorService = services.getExecutorService();

        CanExecuteCommandsAtFixedRate fixedRateExecutor = new ThreadPoolFixedRateExecutor(executorService);
        MediaRepositoryStub mediaRepository = MediaRepositoryStub.createMediaRepository();

        new EventBusLog(logger, bus);

        new ApplicationCore(bus, androidMediaPlayerFactory, fixedRateExecutor, mediaRepository);

        logger.debug("APP CREATED");
    }
}
