package uk.co.rossbeazley.redux.eventbus.executor;

import java.util.concurrent.Executor;

public interface CanDiscoverExecutor {
    Executor executor();

    CanDiscoverExecutor DEFAULT = new CanDiscoverExecutor() {
        public Executor executor() {
            return new Executor() {
                public void execute(Runnable runnable) {
                    runnable.run();
                }
            };
        }
    };
}
