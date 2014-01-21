package uk.co.rossbeazley.avp.eventbus.executor;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class RobolectricLooperLearningTest {

    boolean RUN = false;

    @Test
    public void someLooperTest() {
        Looper otherLooper = givenAPausedLooperThread();
        whenIPostAMessage(otherLooper);
        thenTheMessageIsNotRun();
        butWhenILetTheLooperRun(otherLooper);
        thenTheMessageIsRun();
        Robolectric.shadowOf(otherLooper).quit();
    }

    private void thenTheMessageIsRun() {
        assertThat(RUN,is(true));
    }

    private void butWhenILetTheLooperRun(Looper otherLooper) {
        Robolectric.shadowOf(otherLooper).runOneTask();
    }

    private void thenTheMessageIsNotRun() {
        assertThat(RUN,is(false));
    }

    private void whenIPostAMessage(Looper otherLooper) {
        Handler handler = new Handler(otherLooper);
        handler.post(new Runnable() {
            @Override
            public void run() {
                RUN = true;
            }
        });


    }

    private Looper givenAPausedLooperThread() {
        HandlerThread thread = new HandlerThread("OTHER");
        thread.start();
        Looper otherLooper = thread.getLooper();

        Robolectric.shadowOf(otherLooper).pause();
        return otherLooper;
    }

}
