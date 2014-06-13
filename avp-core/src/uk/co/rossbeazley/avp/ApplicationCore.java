package uk.co.rossbeazley.avp;

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
import uk.co.rossbeazley.avp.android.search.*;
import uk.co.rossbeazley.avp.eventbus.EventBus;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 09/06/2014
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationCore {


    public final MediaPlayerCreator mediaPlayerCreator;
    public final MediaPlayerPreparer mediaPlayerPreparer;
    public final MediaPlayerAutoPlay mediaPlayerAutoPlay;
    public final MediaPlayerControl mediaPlayerControl;
    public final MediaPlayerTimePositionWatcher mediaPlayerTimePositionWatcher;
    public final MediaPlayerScrubber mediaPlayerScrubber;
    public final MediaPlayerStateEventDispatcher mediaPlayerStateEventDispatcher;
    public final Search search;

    // imperative shell
    public final CanDispatchSearchQuery searchService;
    public final CurrentSearchResults currentSearchResults;

    public ApplicationCore(EventBus bus, MediaPlayerFactory androidMediaPlayerFactory, CanExecuteCommandsAtFixedRate fixedRateExecutor, MediaRepository mediaRepository) {
        mediaPlayerCreator = new MediaPlayerCreator(bus, androidMediaPlayerFactory);
        mediaPlayerPreparer = new MediaPlayerPreparer(bus);
        mediaPlayerAutoPlay = new MediaPlayerAutoPlay(bus);
        mediaPlayerControl = new MediaPlayerControl(bus);

        mediaPlayerTimePositionWatcher = new MediaPlayerTimePositionWatcher(fixedRateExecutor, bus);
        mediaPlayerScrubber = new MediaPlayerScrubber(bus);
        mediaPlayerStateEventDispatcher = new MediaPlayerStateEventDispatcher(bus, fixedRateExecutor);

        search = new Search(mediaRepository, bus);

        searchService = new SearchService(bus);
        currentSearchResults = search;
    }
}
