package uk.co.rossbeazley.avp.android.ui.home;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class HomeScreenPresenter {
    public HomeScreenPresenter(HomeScreenView view, final EventBus bus) {
        view.setSearchEventListener(new HomeScreenView.CanListenForUserSearchEvents() {
            @Override
            public void userPressedSearch() {
                UriString uriString = new UriString("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4");
                bus.sendPayload(uriString)
                        .withEvent(Events.USER_LOAD_VIDEO);
            }
        });
    }
}
