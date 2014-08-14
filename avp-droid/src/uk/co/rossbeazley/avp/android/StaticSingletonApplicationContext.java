package uk.co.rossbeazley.avp.android;

import android.app.Application;

public final class StaticSingletonApplicationContext extends Application {

    public static Application instance;
    {
        instance=this;
    }
}
