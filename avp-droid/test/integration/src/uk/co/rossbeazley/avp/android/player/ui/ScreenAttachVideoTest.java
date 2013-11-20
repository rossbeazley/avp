package uk.co.rossbeazley.avp.android.player.ui;

import android.view.View;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.CanInflateLayout;
import uk.co.rossbeazley.avp.android.ui.videoplayer.RenderedVideoOutput;
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

    private ViewGroup VIEW_GROUP_TO_FIND = new ViewGroup(Robolectric.application ) {
        protected void onLayout(boolean changed, int l, int t, int r, int b) {}
        public String toString() { return "ATTACHED TO VIEWGROUP";}
    };

    @Test
    public void videoIsAddedToContainerViewGroup() {
        RenderedVideoOutput mockVideoOutput = new RenderedVideoOutput() {
            @Override
            public void attachToViewGroup(ViewGroup container) {
                viewGroupAttachedTo = container;
            }
        };

        videoOutputScreen.attachVideo(mockVideoOutput);
        assertThat(viewGroupAttachedTo,is(VIEW_GROUP_TO_FIND));
    }


    @Before
    public void setUp() throws Exception {
        CanInflateLayout UNUSED_LAYOUT_INFLATOR = null;
        videoOutputScreen = new VideoScreenViewRenderer(UNUSED_LAYOUT_INFLATOR, this);
    }

    private VideoOutputScreen videoOutputScreen;

    @Override
    public View findViewById(int id) {
        return VIEW_GROUP_TO_FIND;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
