package uk.co.rossbeazley.redux.eventbus.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.test.AndroidTestCase;

import java.util.concurrent.Executor;

public class ExecutorRunsOnTheLooperItsCreatedInTest extends AndroidTestCase {

    public static final String THREAD_NAME = "test thread";

    Looper notMainLooper = null;
    private CanDiscoverExecutor canDiscoverExecutor;
    private Executor looperExecutor;
    private HandlerThread handlerThread;

    private String threadCalledOn = "WRONG THREAD";

    private final Object lock = new Object();
    private boolean notCompleted;

    public void testLooperCanBeDiscoveredByLooperExecutor() {
        givenAnExecutor();
        andALooperThread();
        whenIGetAnExecutorInThatLooperThread();
        andPostAJobToThatExecutor();
        assertTheThreadUsedForExecutionIsTheLooperThread();
    }


    private void givenAnExecutor() {
        canDiscoverExecutor = createExecutorThatFindsThreadLoopers();
    }

    //fake it, then make it
    private CanDiscoverExecutor createExecutorThatFindsThreadLoopers() {
        return new CanDiscoverExecutor() {
            public Executor executor() {
                Executor result = createLooperExecutor();
                return result;
            }

            private Executor createLooperExecutor() {
                return new Executor() {
                    Looper looper = Looper.myLooper();
                    Handler handler = new Handler(looper);

                    public void execute(Runnable runnable) {
                        handler.post(runnable);
                    }
                };
            }
        };
    }

    private void andALooperThread() {
        handlerThread = new HandlerThread(THREAD_NAME);
        handlerThread.start();
        notMainLooper = handlerThread.getLooper();
    }

    private void whenIGetAnExecutorInThatLooperThread() {
        resetLock();
        Runnable taskToGetExecutorInLooperThread = new Runnable() { public void run() {
                looperExecutor = canDiscoverExecutor.executor();
                releaseLock();
            }
        };

        Handler handler = new Handler(notMainLooper);
        handler.post(taskToGetExecutorInLooperThread);
        waitForLockToRelease();
    }


    private void andPostAJobToThatExecutor() {
        resetLock();
        looperExecutor.execute(new Runnable() {
            @Override
            public void run() {
                 threadCalledOn = Thread.currentThread().getName();
                releaseLock();
            }
        });
        waitForLockToRelease();
    }

    private void resetLock() {
        notCompleted = true;
    }

    private void releaseLock() {
        synchronized (lock) {
            notCompleted = false;
            lock.notifyAll();
        }
    }

    private void waitForLockToRelease() {
        synchronized (lock) {
            try {
                while(notCompleted) {
                    lock.wait();
                }
            } catch (InterruptedException ignored) {
                throw new RuntimeException(ignored);
            }
        }
    }

    private void assertTheThreadUsedForExecutionIsTheLooperThread() {
        assertEquals(THREAD_NAME, threadCalledOn);
    }
}
