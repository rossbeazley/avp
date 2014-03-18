package uk.co.rossbeazley.avp.android.ui.results;


import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class InjectableResultsFragmentInjector implements DependenciesService.Injector<InjectableResultsFragment> {
    private EventBus bus;

    public InjectableResultsFragmentInjector(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void inject(InjectableResultsFragment object) {
        object.injectFragmentScreenFactory(new ResultsFragmentScreenFactory(bus));
    }
}
