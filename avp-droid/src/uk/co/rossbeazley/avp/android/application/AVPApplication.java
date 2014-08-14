package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.scrub.MediaPlayerScrubber;
import uk.co.rossbeazley.avp.android.player.state.MediaPlayerStateEventDispatcher;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;
import uk.co.rossbeazley.avp.android.player.time.MediaPlayerTimePositionWatcher;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public final class AVPApplication {

    public final ApplicationCore core;

    public final MediaPlayerCreator mediaPlayerCreator;
    public final MediaPlayerPreparer mediaPlayerPreparer;
    public final MediaPlayerAutoPlay mediaPlayerAutoPlay;
    public final MediaPlayerControl mediaPlayerControl;
    public final MediaPlayerTimePositionWatcher mediaPlayerTimePositionWatcher;
    public final MediaPlayerScrubber mediaPlayerScrubber;
    public final MediaPlayerStateEventDispatcher mediaPlayerStateEventDispatcher;

    public AVPApplication(EventBus bus, Logger logger, MediaPlayerFactory androidMediaPlayerFactory, CanExecuteCommandsAtFixedRate fixedRateExecutor, MediaRepository mediaRepository) {

        mediaPlayerCreator = new MediaPlayerCreator(bus, androidMediaPlayerFactory);
        mediaPlayerPreparer = new MediaPlayerPreparer(bus);
        mediaPlayerAutoPlay = new MediaPlayerAutoPlay(bus);
        mediaPlayerControl = new MediaPlayerControl(bus);

        mediaPlayerTimePositionWatcher = new MediaPlayerTimePositionWatcher(fixedRateExecutor, bus);
        mediaPlayerScrubber = new MediaPlayerScrubber(bus);
        mediaPlayerStateEventDispatcher = new MediaPlayerStateEventDispatcher(bus, fixedRateExecutor);

        core = new ApplicationCore(bus, mediaRepository);

        logger.debug("APP CREATED");
    }
}
