package uk.co.rossbeazley.avp;

import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.scrub.MediaPlayerScrubber;
import uk.co.rossbeazley.avp.android.player.state.MediaPlayerStateEventDispatcher;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;
import uk.co.rossbeazley.avp.android.player.time.MediaPlayerTimePositionWatcher;
import uk.co.rossbeazley.avp.android.search.Search;
import uk.co.rossbeazley.avp.eventbus.EventBus;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 09/06/2014
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationCore {


    public ApplicationCore(EventBus bus, MediaPlayerFactory androidMediaPlayerFactory, CanExecuteCommandsAtFixedRate fixedRateExecutor, MediaRepository mediaRepository) {
        new MediaPlayerCreator(bus, androidMediaPlayerFactory);
        new MediaPlayerPreparer(bus);
        new MediaPlayerAutoPlay(bus);
        new MediaPlayerControl(bus);

        new MediaPlayerTimePositionWatcher(fixedRateExecutor, bus);
        new MediaPlayerScrubber(bus);
        new MediaPlayerStateEventDispatcher(bus, fixedRateExecutor);

        new Search(mediaRepository, bus);
    }
}
