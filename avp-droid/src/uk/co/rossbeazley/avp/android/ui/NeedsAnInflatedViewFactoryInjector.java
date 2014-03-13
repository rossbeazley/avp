package uk.co.rossbeazley.avp.android.ui;

import uk.co.rossbeazley.avp.android.application.DependencyInjectorMap;

public class NeedsAnInflatedViewFactoryInjector implements DependencyInjectorMap.Injector<NeedsAnInflatedViewFactory> {
    @Override
    public void inject(NeedsAnInflatedViewFactory object) {
        object.injectInflatedViewFactory(new DefaultInflatedViewFactory());
    }
}
