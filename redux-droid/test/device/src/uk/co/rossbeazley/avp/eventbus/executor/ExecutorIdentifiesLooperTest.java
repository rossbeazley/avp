package uk.co.rossbeazley.avp.eventbus.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class ExecutorIdentifiesLooperTest extends AndroidTestCase {

    Looper discoveredLooper = null;
    Looper notMainLooper = null;
    boolean registered = false;
    private CanBuildExecutor looperExecutor;
    private HandlerThread handlerThread;

    private final Object lock = new Object();

    public void testLooperCanBeDiscoveredByLooperExecutor() throws InterruptedException {
        givenAnExecutor();
        andALooperThread();
        whenTheExecutorIsCalledWithinTheLooper();
        assertTheLooperIdentifiedByTheExecutorIsTheSameOne();
    }

    private void givenAnExecutor() {
        looperExecutor =  new CanBuildExecutor() {
            public Executor executor() {
                discoveredLooper = Looper.myLooper();
                return null;
            }
        };
    }

    private void andALooperThread() {
        handlerThread = new HandlerThread("test thread");
        handlerThread.start();
        notMainLooper = handlerThread.getLooper();
    }

    private void whenTheExecutorIsCalledWithinTheLooper() throws InterruptedException {
        Handler handler = new Handler(notMainLooper);

        final CountDownLatch latch = new CountDownLatch(1);

        handler.post(new Runnable() {
            @Override
            public void run() {
                registered = true;
                looperExecutor.executor();
                latch.countDown();
            }
        });

        latch.await();
    }

    private void assertTheLooperIdentifiedByTheExecutorIsTheSameOne() {
        assertEquals("executor not even run", true, registered);
        assertEquals("discovered looper not the one from the handler thread", notMainLooper, discoveredLooper);
    }


}
