package uk.co.rossbeazley.avp.android.ui.results;


import org.junit.Test;
import uk.co.rossbeazley.avp.android.application.ApplicationServices;
import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.android.application.DependencyInjectionFrameworkFactory;
import uk.co.rossbeazley.avp.android.application.NullApplicationServices;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultsFragmentInjectorTest implements InjectableResultsFragment {

    private FragmentScreenFactory fragmentScreenFactory;

    @Test
    public void injectIntegratedTest() {
        ApplicationServices services = new NullApplicationServices();
        DependenciesService ds = new DependencyInjectionFrameworkFactory().createDependencyInjectionFramework(services);

        ds.injectDependencies(this);

        assertThat(fragmentScreenFactory, is(ResultsFragmentScreenFactory.class));
    }

    @Override
    public void injectFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }
}
