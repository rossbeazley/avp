package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ui.screenStack.EmptyFragmentBackStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

class ApplicationExit {

    public ApplicationExit(EventBus eventbus, CanFinishTheApp main) {
        this.bindToUiClosedEvent(eventbus, main);
    }

    public void bindToUiClosedEvent(EventBus eventBus, final CanFinishTheApp theApp) {
        eventBus.whenEvent(EmptyFragmentBackStack.UI_CLOSED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        theApp.finish();
                    }
                });
    }
}
