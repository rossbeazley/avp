package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.application.DependencyInjectors;
import uk.co.rossbeazley.avp.eventbus.EventBus;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 30/01/2014
* Time: 13:02
* To change this template use File | Settings | File Templates.
*/
public class VideoPlayerFragmentInjector implements DependencyInjectors.Injector<InjectableVideoPlayerFragment> {

    private EventBus eventBus;

    public VideoPlayerFragmentInjector(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void inject(InjectableVideoPlayerFragment fragment) {
        VideoPlayerFragmentScreenFactory fragmentScreenFactory = new VideoPlayerFragmentScreenFactory(eventBus);
        fragment.setFragmentScreenFactory(fragmentScreenFactory);
    }
}
