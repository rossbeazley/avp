package uk.co.rossbeazley.avp.android.player.ui;

import android.view.View;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoOutputScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreenViewRenderer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)

public class ScreenAttachVideoTest implements CanFindViewById {

    private ViewGroup viewGroupAttachedTo = new ViewGroup(Robolectric.application ) {
        protected void onLayout(boolean changed, int l, int t, int r, int b) {}
        public String toString() { return "NOTHING ATTACHED";}
    };

    private ViewGroup VIEW_GROUP_TO_FIND = new ViewGroup(Robolectric.application) {
        protected void onLayout(boolean changed, int l, int t, int r, int b) {}
        public String toString() { return "ATTACHED TO VIEWGROUP"; }
    };

    private ActivityForTestingViews activity;
    private VideoOutputScreen videoOutputScreen;

    @Test
    public void videoIsAddedToContainerViewGroup() {
        RenderedVideoOutput mockVideoOutput = new RenderedVideoOutput() {
            @Override
            public void attachToViewGroup(ViewGroup container) {
                viewGroupAttachedTo = container;
            }
        };

        videoOutputScreen.attachVideo(mockVideoOutput);
        assertThat(viewGroupAttachedTo, is(VIEW_GROUP_TO_FIND));
    }

    @Before
    public void setUp() throws Exception {
        activity = ActivityTestSupport.createVisibleActivityForLayout(R.layout.videoplayer);
        videoOutputScreen = new VideoScreenViewRenderer(this);
    }

    @Override
    public View findViewById(int id) {
        if (id == R.id.videocontainer)
            return VIEW_GROUP_TO_FIND;
        else
            return activity.viewFinder().findViewById(id);
    }
}
