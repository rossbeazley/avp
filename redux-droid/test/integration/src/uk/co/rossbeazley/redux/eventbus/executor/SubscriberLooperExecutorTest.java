package uk.co.rossbeazley.redux.eventbus.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.concurrent.Executor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SubscriberLooperExecutorTest {

    Looper actualLooper = null;
    boolean registered = false;
    private CanDiscoverExecutor looperExecutor;

    @Test
    public void aLooperCanBeDiscoveredByLooperExecutor() {
        looperExecutor = givenALooperExecutor();
        Looper expectedLooper = andALooperThread();
        whenTheLooperGetsTheExecutor(expectedLooper);
        thenTheLooperIdentifiedByTheExecutorIsTheSameOne(expectedLooper);
    }

    private void thenTheLooperIdentifiedByTheExecutorIsTheSameOne(Looper expectedLooper) {
        assertThat(registered, is(true));
        assertThat(actualLooper, is(expectedLooper));
    }

    private void whenTheLooperGetsTheExecutor(Looper expectedLooper) {
        Handler handler = new Handler(expectedLooper);
        handler.post(new Runnable() {
            @Override
            public void run() {
                looperExecutor.executor();
                registered=true;
            }
        });
        Robolectric.shadowOf(expectedLooper).runOneTask();
    }

    private Looper andALooperThread() {
        HandlerThread thread = new HandlerThread("test thread");
        thread.start();
        Looper expectedLooper = thread.getLooper();
        Robolectric.shadowOf(expectedLooper).pause();
        return expectedLooper;
    }

    private CanDiscoverExecutor givenALooperExecutor() {
        return new CanDiscoverExecutor() {

            @Override
            public Executor executor() {
                actualLooper = Looper.myLooper();
                return null;
            }
        };
    }
}
