package uk.co.rossbeazley.avp.android.ui;

import uk.co.rossbeazley.avp.android.application.DependenciesService;

public class NeedsAnInflatedViewFactoryInjector implements DependenciesService.Injector<NeedsAnInflatedViewFactory> {
    @Override
    public void inject(NeedsAnInflatedViewFactory object) {
        object.injectInflatedViewFactory(new DefaultInflatedViewFactory());
    }
}
