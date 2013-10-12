package uk.co.rossbeazley.redux.android.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public abstract class EmptyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    public void onActivityCreated(Activity activity, Bundle bundle) {}

    public void onActivityStarted(Activity activity) {}

    public void onActivityResumed(Activity activity) {}

    public void onActivityPaused(Activity activity) {}

    public void onActivityStopped(Activity activity) {}

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    public void onActivityDestroyed(Activity activity) {}
}
