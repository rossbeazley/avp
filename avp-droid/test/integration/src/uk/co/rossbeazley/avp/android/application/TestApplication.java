package uk.co.rossbeazley.avp.android.application;

import android.app.Application;

public class TestApplication extends Application {

    public TestApplication() {
        ProductionApplicationServices services = new ProductionApplicationServices(this) {
            @Override
            public void executeBlockingRunnableNotOnMainThread(Runnable runnable) {
                runnable.run();
            }
        };
    }

}
