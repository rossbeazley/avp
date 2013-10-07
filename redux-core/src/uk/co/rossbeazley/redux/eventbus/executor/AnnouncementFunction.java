package uk.co.rossbeazley.redux.eventbus.executor;

interface AnnouncementFunction {
    void invoke();

    void invoke(Object payload);
}
