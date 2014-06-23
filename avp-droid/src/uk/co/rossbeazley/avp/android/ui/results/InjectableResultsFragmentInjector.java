package uk.co.rossbeazley.avp.android.ui.results;


import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class InjectableResultsFragmentInjector implements DependenciesService.Injector<InjectableResultsFragment> {
    private EventBus bus;
    private ApplicationCore applicationCore;

    public InjectableResultsFragmentInjector(EventBus bus, ApplicationCore applicationCore) {
        this.bus = bus;
        this.applicationCore = applicationCore;
    }

    @Override
    public void inject(InjectableResultsFragment object) {
        object.injectFragmentScreenFactory(new ResultsFragmentScreenFactory(bus, applicationCore));
    }
}
