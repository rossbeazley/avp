package uk.co.rossbeazley.avp.android.player.render;

import android.test.ActivityInstrumentationTestCase2;
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


    public void testAttachToViewGroupTest() {
        final ViewGroup linearLayout = (ViewGroup) act.findViewById(android.R.id.content);
        final RenderedVideoOutput view = new AndroidMediaPlayerVideoOutputFactory().createAndroidMediaPlayerVideoOutput(this);
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.attachToViewGroup(linearLayout);
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
