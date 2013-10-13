package uk.co.rossbeazley.redux.android.videoplayer;

import uk.co.rossbeazley.redux.UriString;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.FunctionWithParameter;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 24/09/13
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class VideoPreparerEventDispatcher {

    public VideoPreparerEventDispatcher(final EventBus bus, final VideoPreparer videoPreparer) {
        bus.whenEvent("load_video").thenRun(new FunctionWithParameter<UriString>() {
            @Override
            public void invoke(UriString object) {
                videoPreparer.playVideoUrl(object);
            }
        });
    }
}
