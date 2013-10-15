package uk.co.rossbeazley.redux.android.application;

public class TestApplication extends Application {

    @Override
    protected void buildApplication() {
        registerActivityLifecycleCallbacks(new ActivityWiringAspect(this, getLogger()));
        createApplication();
    }
}
