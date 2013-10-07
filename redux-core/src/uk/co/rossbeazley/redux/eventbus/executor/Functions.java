package uk.co.rossbeazley.redux.eventbus.executor;

import java.util.LinkedList;
import java.util.List;

class Functions {

    private List<AnnouncementFunction> functions = new LinkedList<AnnouncementFunction>();

    void addSubscriber(AnnouncementFunction subscriber) {
        functions.add(subscriber);
    }

    void invoke() {
        for (AnnouncementFunction function : functions) {
            function.invoke();
        }
    }

    void invoke(Object payload) {
        for (AnnouncementFunction function : functions) {
            function.invoke(payload);
        }
    }
}
