package uk.co.rossbeazley.avp.android.ui.results;


import org.junit.Test;
import uk.co.rossbeazley.avp.android.application.*;
import uk.co.rossbeazley.avp.android.ui.DefaultInflatedViewFactory;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedViewFactory;
import uk.co.rossbeazley.avp.android.ui.NeedsAnInflatedViewFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultsFragmentInjectorTest implements InjectableResultsFragment, NeedsAnInflatedViewFactory {

    private FragmentScreenFactory fragmentScreenFactory;
    private InflatedViewFactory inflatedViewFactory;



    @Test
    public void injectIntegratedTest() {
        ApplicationServices services = new NullApplicationServices();
        DependenciesService ds = new DependencyInjectionFrameworkFactory().createDependencyInjectionFramework(services);

        ds.injectDependencies(this);

        assertThat(fragmentScreenFactory, is(ResultsFragmentScreenFactory.class));
        assertThat(inflatedViewFactory,is(DefaultInflatedViewFactory.class));
    }



    @Override
    public void injectFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    @Override      //TODO figure out what the (intergrated) test is im missing
    public void injectInflatedViewFactory(InflatedViewFactory inflatedViewFactory) {
        this.inflatedViewFactory = inflatedViewFactory;
    }

          /*
    private class ResultsFragmentInjector implements DependencyInjectorMap.Injector<InjectableResultsFragment> {
        public ResultsFragmentInjector() {
        }

        @Override
        public void inject(InjectableResultsFragment object) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }   */

}
