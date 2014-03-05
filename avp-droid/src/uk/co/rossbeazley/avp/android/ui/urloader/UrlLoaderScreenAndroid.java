package uk.co.rossbeazley.avp.android.ui.urloader;

import android.view.View;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.ViewFinder;
import uk.co.rossbeazley.avp.android.ui.urlloader.UrlLoaderScreen;

public class UrlLoaderScreenAndroid implements UrlLoaderScreen {
    private final ViewFinder viewFinder;
    private CanListenForUserGoEvents searchEventListener;
    private CanListenForScreenTearDownEvents canListenForScreenTearDownEvents;
    private CanListenForUserGotoSearchScreenEvents gotoSearchEventListener;

    public UrlLoaderScreenAndroid(CanFindViewById inflatedLayoutView) {
        searchEventListener = CanListenForUserGoEvents.NONE;
        viewFinder = new ViewFinder(inflatedLayoutView);

        viewFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEventListener.userPressedGo();
            }
        }, R.id.go);

        viewFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSearchEventListener.userPressedGotoSearch();
            }
        }, R.id.search);

        this.canListenForScreenTearDownEvents = CanListenForScreenTearDownEvents.NONE;
        this.gotoSearchEventListener = CanListenForUserGotoSearchScreenEvents.NONE;

        populateTextBoxWithInitialValue();
    }

    private void populateTextBoxWithInitialValue() {
        viewFinder.setText("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4",R.id.searchString);
    }

    @Override
    public void tearDown() {
        viewFinder.clearOnClickListener(R.id.go);
        canListenForScreenTearDownEvents.screenTearDown();
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        this.canListenForScreenTearDownEvents = canListenForScreenTearDownEvents;
    }

    @Override
    public void setGoEventListener(CanListenForUserGoEvents searchEventListener) {
        this.searchEventListener = searchEventListener;
    }

    @Override
    public String uriString() {
        return viewFinder.getText(R.id.searchString);
    }

    @Override
    public void setGotoSearchEventListener(CanListenForUserGotoSearchScreenEvents gotoSearchEventListener) {
        this.gotoSearchEventListener = gotoSearchEventListener;
    }


}
