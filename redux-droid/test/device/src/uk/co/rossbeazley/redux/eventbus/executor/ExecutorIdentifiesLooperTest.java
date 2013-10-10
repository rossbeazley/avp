package uk.co.rossbeazley.redux.eventbus.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.test.AndroidTestCase;

import java.util.concurrent.Executor;

public class ExecutorIdentifiesLooperTest extends AndroidTestCase {

    Looper discoveredLooper = null;
    Looper notMainLooper = null;
    boolean registered = false;
    private CanDiscoverExecutor looperExecutor;
    private HandlerThread handlerThread;

    private final Object lock = new Object();

    public void testLooperCanBeDiscoveredByLooperExecutor() {
        givenAnExecutor();
        andALooperThread();
        whenTheExecutorIsCalledWithinTheLooper();
        waitForLockToRelease();
        assertTheLooperIdentifiedByTheExecutorIsTheSameOne();
    }

    private void givenAnExecutor() {
        looperExecutor =  new CanDiscoverExecutor() {
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

    private void whenTheExecutorIsCalledWithinTheLooper() {
        Handler handler = new Handler(notMainLooper);
        handler.post(new Runnable() {
            @Override
            public void run() {
                registered = true;
                looperExecutor.executor();
                releaseLock();
            }

            private void releaseLock() {
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        });
    }

    private void waitForLockToRelease() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException ignored) {
                throw new RuntimeException(ignored);
            }
        }
    }

    private void assertTheLooperIdentifiedByTheExecutorIsTheSameOne() {
        assertEquals("executor not even run", true, registered);
        assertEquals("discovered looper not the one from the handler thread", notMainLooper, discoveredLooper);
    }


}
