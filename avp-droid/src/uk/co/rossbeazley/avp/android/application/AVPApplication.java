package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.log.EventBusLog;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerAutoPlay;
import uk.co.rossbeazley.avp.android.player.control.MediaPlayerControl;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerCreator;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.render.AndroidMediaPlayerVideoOutputFactory;
import uk.co.rossbeazley.avp.android.player.render.MediaPlayerViewCreator;
import uk.co.rossbeazley.avp.android.player.scrub.MediaPlayerScrubber;
import uk.co.rossbeazley.avp.android.player.state.MediaPlayerStateEventDispatcher;
import uk.co.rossbeazley.avp.android.player.time.MediaPlayerTimePositionWatcher;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.search.Search;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.HashMap;

public class AVPApplication {

    // Is this lazy?    should each service be a constructor arg
    public AVPApplication(final ApplicationServices services) {

        EventBus bus = services.eventbus();
        Logger logger = services.getLogger();


        new MediaPlayerCreator(bus, services.getAndroidMediaPlayerFactory());
        new MediaPlayerPreparer(bus);
        new MediaPlayerAutoPlay(bus);
        new MediaPlayerControl(bus);

        ThreadPoolFixedRateExecutor fixedRateExecutor = new ThreadPoolFixedRateExecutor(services.getExecutorService());
        new MediaPlayerTimePositionWatcher(fixedRateExecutor, bus);
        new MediaPlayerScrubber(bus);
        new MediaPlayerStateEventDispatcher(bus, fixedRateExecutor);

        //This isnt in the core module, its only in droid
        new MediaPlayerViewCreator(new AndroidMediaPlayerVideoOutputFactory(), bus);

        new EventBusLog(logger, bus);

        new Search(createMediaRepository(), bus);

        logger.debug("APP CREATED");
    }

    private MediaRepositoryStub createMediaRepository() {
        return new MediaRepositoryStub(new HashMap<Query, Results>(){{
            put(Query.fromString("ross"),
                    new Results(new MediaItem("Item 1"),
                                new MediaItem("Item 2"),
                                new MediaItem("Item 3"),
                                new MediaItem("Item 4"),
                                new MediaItem("Item 5"),
                                new MediaItem("Item 6")));
        }});
    }

}
