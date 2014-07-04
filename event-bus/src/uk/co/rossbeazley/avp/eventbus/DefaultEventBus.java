package uk.co.rossbeazley.avp.eventbus;

import uk.co.rossbeazley.avp.eventbus.singlethreaded.PayloadFunction;

public class DefaultEventBus implements EventBus {


    @Override
    public void announce(Object event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EventSubscription whenEvent(Object event) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AnnouncementWithPayload sendPayload(Object any_object) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void registerProducer(Object event, PayloadFunction function) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
