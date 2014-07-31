package uk.co.rossbeazley.avp.android;

import android.app.Application;

public class StaticSingletonApplicationContext extends Application {

    public static Application instance;
    {
        instance=this;
    }
}
