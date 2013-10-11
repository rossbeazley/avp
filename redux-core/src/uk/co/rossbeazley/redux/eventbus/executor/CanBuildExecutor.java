package uk.co.rossbeazley.redux.eventbus.executor;

import java.util.concurrent.Executor;

public interface CanBuildExecutor {
    Executor executor();

    CanBuildExecutor DEFAULT = new CanBuildExecutor() {
        public Executor executor() {
            return new Executor() {
                public void execute(Runnable runnable) {
                    runnable.run();
                }
            };
        }
    };
}
