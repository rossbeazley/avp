package uk.co.rossbeazley.avp.android.player.render;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import uk.co.rossbeazley.avp.android.activity.Main;

public class AttachViewTest extends ActivityInstrumentationTestCase2<Main> implements CanAttachToAndroidView {

    private boolean setDisplayCalled;
    private LinearLayout LL;
    private Main act;

    public AttachViewTest() {
        super(Main.class);
    }


    @UiThreadTest
    public void testAttachToViewGroupTest() {
        ViewGroup linearLayout = (ViewGroup) act.findViewById(android.R.id.content);
        RenderedVideoOutput view = new AndroidMediaPlayerVideoOutputFactory().createAndroidMediaPlayerVideoOutput(this);
        view.attachToViewGroup(linearLayout);
        assertTrue(setDisplayCalled);
    }

    public void setUp() {
        act=getActivity();
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {
        setDisplayCalled = true;
    }
}
