package uk.co.rossbeazley.redux.eventbus.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.test.AndroidTestCase;

import java.util.concurrent.Executor;

public class ExecutorRunsOnTheLooperItsCreatedInTest extends AndroidTestCase {

    Looper discoveredLooper = null;
    Looper notMainLooper = null;
    boolean registered = false;
    private CanDiscoverExecutor canDiscoverExecutor;
    private Executor looperExecutor;
    private HandlerThread handlerThread;

    private final Object lock = new Object();

    public void testLooperCanBeDiscoveredByLooperExecutor() {
        givenAnExecutor();
        andALooperThread();
        whenIGetAnExecutorInThatLooperThread();
        //waitForLockToRelease();
        andPostAJobToThatExecutor();
        assertTheLooperUsedForExecutionIsTheLoopThread();
    }


    private void givenAnExecutor() {
        canDiscoverExecutor =  new CanDiscoverExecutor() {

            public Executor executor() {
                Executor result;

                result = new Executor() {

                    Looper looper = Looper.myLooper();
                    Handler handler = new Handler(looper);

                    @Override
                    public void execute(Runnable runnable) {
                        handler.post(runnable);
                    }
                };

                return result;
            }
        };
    }

    private void andALooperThread() {
        handlerThread = new HandlerThread("test thread");
        handlerThread.start();
        notMainLooper = handlerThread.getLooper();
    }

    private void whenIGetAnExecutorInThatLooperThread() {
        Handler handler = new Handler(notMainLooper);
        handler.post(new Runnable() {
            @Override
            public void run() {
                looperExecutor = canDiscoverExecutor.executor();
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


    private void andPostAJobToThatExecutor() {
        looperExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private void assertTheLooperUsedForExecutionIsTheLoopThread() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
