package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.android.application.DependencyInjectors;

public class InjectableResultsFragmentInjector implements DependencyInjectors.Injector<InjectableResultsFragment> {
    @Override
    public void inject(InjectableResultsFragment object) {
        object.injectFragmentScreenFactory(new ResultsFragmentScreenFactory());
    }
}
