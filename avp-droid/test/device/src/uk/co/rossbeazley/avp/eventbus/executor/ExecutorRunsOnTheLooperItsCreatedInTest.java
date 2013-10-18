package uk.co.rossbeazley.avp.eventbus.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class ExecutorRunsOnTheLooperItsCreatedInTest extends AndroidTestCase {

    public static final String THREAD_NAME = "test thread";
    private String threadCalledOn = "WRONG THREAD";

    Looper notMainLooper = null;
    private CanBuildExecutor canBuildExecutor;
    private Executor looperExecutor;


    public void testLooperCanBeDiscoveredByLooperExecutor() throws InterruptedException {
        givenAnExecutor();
        andALooperThread();
        whenIGetAnExecutorInThatLooperThread();
        andPostAJobToThatExecutor();
        assertTheThreadUsedForExecutionIsTheLooperThread();
    }

    private void givenAnExecutor() {
        canBuildExecutor = new LooperExecutorFactory();
    }

    private void andALooperThread() {
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
        handlerThread.start();
        notMainLooper = handlerThread.getLooper();
    }

    private void whenIGetAnExecutorInThatLooperThread() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Runnable taskToGetExecutorInLooperThread = new Runnable() { public void run() {
                looperExecutor = canBuildExecutor.executor();
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

}
