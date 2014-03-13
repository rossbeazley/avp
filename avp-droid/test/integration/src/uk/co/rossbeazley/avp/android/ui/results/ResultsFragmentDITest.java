package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultsFragmentDITest {

    @Test
    public void resultsFragmentDependencyRegistered() {

        Object injector = null;
        assertThat(injector,is(InjectableResultsFragmentInjector.class));
    }
}
