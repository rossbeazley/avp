package uk.co.rossbeazley.avp.eventbus.executor;

interface AnnouncementFunction {
    void invoke();

    void invoke(Object payload);
}
