package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.log.EventBusLog;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class AVPApplication {

    public final ApplicationCore core;

    public AVPApplication(EventBus bus, Logger logger, MediaPlayerFactory androidMediaPlayerFactory, CanExecuteCommandsAtFixedRate fixedRateExecutor, MediaRepository mediaRepository) {
        core = new ApplicationCore(bus, androidMediaPlayerFactory, fixedRateExecutor, mediaRepository);

        logger.debug("APP CREATED");
    }
}
