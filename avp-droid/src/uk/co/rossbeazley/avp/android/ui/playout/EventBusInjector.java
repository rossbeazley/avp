package uk.co.rossbeazley.avp.android.ui.playout;

import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public final class EventBusInjector implements DependenciesService.Injector<InjectableEventBus> {
    private final EventBus eventbus;

    public EventBusInjector(EventBus eventbus) {
        this.eventbus = eventbus;
    }

    @Override
    public void inject(InjectableEventBus object) {
        object.injectEventBus(eventbus);
    }
}
