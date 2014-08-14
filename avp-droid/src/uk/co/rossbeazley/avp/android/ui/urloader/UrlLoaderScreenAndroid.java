package uk.co.rossbeazley.avp.android.ui.urloader;

import android.view.View;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.ViewFinder;
import uk.co.rossbeazley.avp.android.ui.urlloader.UrlLoaderScreen;

public final class UrlLoaderScreenAndroid implements UrlLoaderScreen {
    private final ViewFinder viewFinder;
    private CanListenForUserGoEvents searchEventListener;
    private CanListenForScreenTearDownEvents canListenForScreenTearDownEvents;
    private CanListenForUserGotoSearchScreenEvents gotoSearchEventListener;

    public UrlLoaderScreenAndroid(CanFindViewById inflatedLayoutView) {
        searchEventListener = CanListenForUserGoEvents.NONE;
        this.canListenForScreenTearDownEvents = CanListenForScreenTearDownEvents.NONE;
        this.gotoSearchEventListener = CanListenForUserGotoSearchScreenEvents.NONE;

       viewFinder = new ViewFinder(inflatedLayoutView); //at some point ill pass this in instead of inflated view

        bindGoButtonListener();
        bindGotoSearchButtonListener();

        populateTextBoxWithInitialValue();
    }

    private void bindGotoSearchButtonListener() {
        viewFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSearchEventListener.userPressedGotoSearch();
            }
        }, R.id.search);
    }

    private void bindGoButtonListener() {
        viewFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEventListener.userPressedGo();
            }
        }, R.id.go);
    }

    private void populateTextBoxWithInitialValue() {
        final String av_test_harness_sample_video = "http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4";
        viewFinder.setText(av_test_harness_sample_video,R.id.searchString);
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
