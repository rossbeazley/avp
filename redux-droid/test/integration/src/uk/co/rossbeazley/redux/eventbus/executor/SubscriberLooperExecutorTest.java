package uk.co.rossbeazley.redux.eventbus.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.concurrent.Executor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SubscriberLooperExecutorTest {

    private Looper mainLooper = null;
    Looper discoveredLooper = null;
    Looper notMainLooper = null;
    boolean registered = false;
    private CanBuildExecutor looperExecutor;
    private HandlerThread handlerThread;

    @Test @Ignore("Broken in roboelectric")
    public void aLooperCanBeDiscoveredByLooperExecutor() {
        mainLooper = Looper.getMainLooper();
        looperExecutor = givenAnExecutor();
        andALooperThread();
        whenTheExecutorIsCalledWithinTheLooper();
        thenTheLooperIdentifiedByTheExecutorIsTheSameOne();
    }



    private CanBuildExecutor givenAnExecutor() {
        return new CanBuildExecutor() { public Executor executor() {
                discoveredLooper = Looper.myLooper();
                return null;
            }
        };
    }

    private void andALooperThread() {
        handlerThread = new HandlerThread("test thread");
        handlerThread.start();
        notMainLooper = handlerThread.getLooper();
        Robolectric.shadowOf(notMainLooper).pause();
    }

    private void whenTheExecutorIsCalledWithinTheLooper() {

        // Think the handler is getting bound to the main looper, not the one im in
        Handler handler = new Handler(notMainLooper);
        handler.post(new Runnable() {
            @Override
            public void run() {
                looperExecutor.executor();
                registered = true;
            }
        });
        Robolectric.shadowOf(notMainLooper).runOneTask();
    }

    private void thenTheLooperIdentifiedByTheExecutorIsTheSameOne() {
        assertThat(registered, is(true));
        assertThat(discoveredLooper, is(notMainLooper));
    }


}
