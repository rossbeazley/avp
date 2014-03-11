package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultsScreenPresenterTest implements ResultsScreen {

    private static final boolean SHOWN = true;
    private boolean spinner = false;

    @Test
    public void directsViewToDisplaySpinnerAsDefaultState() {
        ResultsScreen screen = this;
        new ResultsScreenPresenter(screen);
        assertThat("spinner shown", spinner,is(SHOWN));
    }

    @Override
    public void showSpinner() {
        spinner = true;
    }

    @Override
    public void tearDown() {
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
    }

}
