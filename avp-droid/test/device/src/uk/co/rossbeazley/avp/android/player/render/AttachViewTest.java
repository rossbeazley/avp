package uk.co.rossbeazley.avp.android.player.render;

import android.test.ActivityInstrumentationTestCase2;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.application.Main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class AttachViewTest extends ActivityInstrumentationTestCase2<Main> implements CanAttachToAndroidView {

    private boolean setDisplayCalled;
    private Main act;

    public AttachViewTest() {
        super(Main.class);
    }


    public void testAttachToViewGroupTest() throws InterruptedException {
        final RenderedVideoOutput view = new AndroidMediaPlayerVideoOutputFactory().createAndroidMediaPlayerVideoOutput(this);

        final ViewGroup viewGroup = (ViewGroup) act.findViewById(android.R.id.content);
        final CountDownLatch latch = new CountDownLatch(1);
        act.runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                view.attachToViewGroup(viewGroup);
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
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
