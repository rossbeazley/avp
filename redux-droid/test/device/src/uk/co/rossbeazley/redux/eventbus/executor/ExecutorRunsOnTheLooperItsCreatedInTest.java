package uk.co.rossbeazley.redux.eventbus.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class ExecutorRunsOnTheLooperItsCreatedInTest extends AndroidTestCase {

    public static final String THREAD_NAME = "test thread";

    Looper notMainLooper = null;
    private CanDiscoverExecutor canDiscoverExecutor;
    private Executor looperExecutor;

    private String threadCalledOn = "WRONG THREAD";


    public void testLooperCanBeDiscoveredByLooperExecutor() throws InterruptedException {
        givenAnExecutor();
        andALooperThread();
        whenIGetAnExecutorInThatLooperThread();
        andPostAJobToThatExecutor();
        assertTheThreadUsedForExecutionIsTheLooperThread();
    }


    private void givenAnExecutor() {
        canDiscoverExecutor = new LooperExecutorFactory();
    }

    private void andALooperThread() {
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
        handlerThread.start();
        notMainLooper = handlerThread.getLooper();
    }

    private void whenIGetAnExecutorInThatLooperThread() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Runnable taskToGetExecutorInLooperThread = new Runnable() { public void run() {
                looperExecutor = canDiscoverExecutor.executor();
            latch.countDown();
            }
        };

        Handler handler = new Handler(notMainLooper);
        handler.post(taskToGetExecutorInLooperThread);
        latch.await();
    }


    private void andPostAJobToThatExecutor() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        looperExecutor.execute(new Runnable() {
            @Override
            public void run() {
                 threadCalledOn = Thread.currentThread().getName();
                latch.countDown();
            }
        });
        latch.await();
    }

    private void assertTheThreadUsedForExecutionIsTheLooperThread() {
        assertEquals(THREAD_NAME, threadCalledOn);
    }

    private static class LooperExecutorFactory implements CanDiscoverExecutor {
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
    }
}
