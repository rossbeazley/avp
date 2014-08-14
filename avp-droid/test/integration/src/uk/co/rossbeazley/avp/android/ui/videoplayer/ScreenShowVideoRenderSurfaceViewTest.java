package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public final class ScreenShowVideoRenderSurfaceViewTest {


    private static final String CALLED = "CALLED";
    private static final String NOT_CALLED = "NOT CALLED";
    private ViewGroup viewGroup;

    //expectations
    private static String surfaceCreated = NOT_CALLED;
    private static String surfaceDestroyed = NOT_CALLED;
    private static String surfaceChanged = NOT_CALLED;

    @Test   @Ignore
    public void videoRenderShownOnScreen() {

        attachToViewGroup(viewGroup);

        assertThat(surfaceDestroyed, is(not(CALLED)));
        assertThat(surfaceChanged, is(not(CALLED)));
        assertThat(surfaceCreated,is(CALLED));
    }

    private void attachToViewGroup(ViewGroup viewGroup) {
        SurfaceView sf = new SurfaceView(viewGroup.getContext());
        sf.setId(R.id.videoview);
        sf.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        SurfaceHolder holder = sf.getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                ScreenShowVideoRenderSurfaceViewTest.surfaceCreated = CALLED;
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                ScreenShowVideoRenderSurfaceViewTest.surfaceChanged = CALLED;
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                ScreenShowVideoRenderSurfaceViewTest.surfaceDestroyed = CALLED;
            }
        });

        viewGroup.addView(sf);
        sf.setVisibility(View.VISIBLE);

    }


    @Before
    public void setUp() throws Exception {
        surfaceCreated = NOT_CALLED;
        surfaceDestroyed = NOT_CALLED;
        surfaceChanged = NOT_CALLED;

        activity = ActivityForTestingViews.createVisibleActivity();
        videoScreen = new VideoScreenAndroidView(activity.viewFinder());
        viewGroup = (ViewGroup) activity.findViewById(R.id.videocontainer);
    }


    private VideoPlayerScreen videoScreen;

    private ActivityForTestingViews activity;

    private View getViewById(int id) {
        return activity.findViewById(id);
    }
}
