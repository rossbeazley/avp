package uk.co.rossbeazley.avp.android.ui;

import uk.co.rossbeazley.avp.android.application.DependencyInjectors;

public class NeedsAnInflatedViewFactoryInjector implements DependencyInjectors.Injector<NeedsAnInflatedViewFactory> {
    @Override
    public void inject(NeedsAnInflatedViewFactory object) {
        object.injectInflatedViewFactory(new DefaultInflatedViewFactory());
    }
}
