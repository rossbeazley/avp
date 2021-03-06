package uk.co.rossbeazley.avp.eventbus.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

public final class LooperExecutorFactory implements CanBuildExecutor {

    public Executor executor() {
        Handler handler = newHandlerForCurrentLooper();
        return new LooperExecutor(handler);
    }

    private Handler newHandlerForCurrentLooper() {
        Looper looper = Looper.myLooper();
        return new Handler(looper);
    }

    private static final class LooperExecutor implements Executor {

        private final Handler handler;

        private LooperExecutor(Handler handler) {
            this.handler = handler;
        }

        public void execute(Runnable runnable) {
            handler.post(runnable);
        }
    }
}
