package uk.co.rossbeazley.avp.android.ui.results;


import uk.co.rossbeazley.avp.android.application.DependenciesService;

public class InjectableResultsFragmentInjector implements DependenciesService.Injector<InjectableResultsFragment> {
    @Override
    public void inject(InjectableResultsFragment object) {
        object.injectFragmentScreenFactory(new ResultsFragmentScreenFactory());
    }
}
