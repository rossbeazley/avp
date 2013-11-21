package uk.co.rossbeazley.avp.android.player.render;

import android.test.ActivityInstrumentationTestCase2;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.activity.Main;

public class AttachViewTest extends ActivityInstrumentationTestCase2<Main> implements CanAttachToAndroidView {

    private boolean setDisplayCalled;
    private Main act;

    public AttachViewTest() {
        super(Main.class);
    }


    public void testAttachToViewGroupTest() {
        final RenderedVideoOutput view = new AndroidMediaPlayerVideoOutputFactory().createAndroidMediaPlayerVideoOutput(this);

        final ViewGroup viewGroup = (ViewGroup) act.findViewById(android.R.id.content);
        act.runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                view.attachToViewGroup(viewGroup);
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
