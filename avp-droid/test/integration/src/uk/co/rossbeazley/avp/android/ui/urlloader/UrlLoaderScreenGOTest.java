package uk.co.rossbeazley.avp.android.ui.urlloader;

import android.view.View;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.urloader.UrlLoaderScreenAndroid;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UrlLoaderScreenGOTest implements UrlLoaderScreen.CanListenForUserGoEvents {

    private boolean called = false;
    private String query_string = "any_old_query_string";
    private ActivityForTestingViews visibleActivityForLayout;
    private UrlLoaderScreen screen;

    @Before
    public void setUp() throws Exception {
        visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.urlloader);//TODO change this to real layout
        screen = new UrlLoaderScreenAndroid(visibleActivityForLayout.viewFinder());
    }

    @Test
    public void clickingGoButtonDispatchesEvent() {
        screen.setGoEventListener(this);
        View goButton = visibleActivityForLayout.findViewById(R.id.go);
        Robolectric.clickOn(goButton);

        assertThat(called,is(true));
    }

    @Test
    public void returnsTextFromTheQueryStringTextBos() {
        TextView tv = (TextView) visibleActivityForLayout.findViewById(R.id.searchString);
        tv.setText(query_string);

        String text;
        text = screen.uriString();
        assertThat(text, is(query_string));
    }

    @Test
    public void newScreenIsPopulatedWithURLForTestContent() {
        String text = screen.uriString();
        assertThat(text, is("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4"));

    }

    @Override
    public void userPressedGo() {
        this.called=true;
    }

}
