package uk.co.rossbeazley.avp.android.application;

public class TestApplication extends Application {

    public TestApplication() {
        services = new ProductionApplicationServices(this) {
            @Override
            public void executeRunnable(Runnable runnable) {
                runnable.run();
            }
        };
    }

}
