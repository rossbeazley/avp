package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.android.application.DependencyInjectorMap;

public class InjectableResultsFragmentInjector implements DependencyInjectorMap.Injector<InjectableResultsFragment> {
    @Override
    public void inject(InjectableResultsFragment object) {
        object.injectFragmentScreenFactory(new ResultsFragmentScreenFactory());
    }
}
