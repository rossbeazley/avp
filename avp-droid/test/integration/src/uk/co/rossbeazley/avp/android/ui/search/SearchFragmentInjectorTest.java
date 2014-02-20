package uk.co.rossbeazley.avp.android.ui.search;


import org.junit.Test;
import uk.co.rossbeazley.avp.android.application.*;
import uk.co.rossbeazley.avp.android.ui.DefaultInflatedViewFactory;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedViewFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchFragmentInjectorTest implements InjectableSearchFragment {

    private FragmentScreenFactory fragmentScreenFactory;
    private InflatedViewFactory inflatedViewFactory;

    @Test
    public void inject() {

        EventBus UNUSED_EVENT_BUS = null;
        SearchFragmentInjector searchFragmentInjector = new SearchFragmentInjector(UNUSED_EVENT_BUS);
        searchFragmentInjector.inject(this);

        assertThat(fragmentScreenFactory, is(SearchFragmentScreenFactory.class));
        assertThat(inflatedViewFactory,is(DefaultInflatedViewFactory.class));
    }

    @Test
    public void injectIntegratedTest() {
        ApplicationServices services = new NullApplicationServices();
        DependenciesService ds = new DependencyInjectionFrameworkFactory().createDependencyInjectionFramework(services);

        ds.injectDependencies(this);

        assertThat(fragmentScreenFactory, is(SearchFragmentScreenFactory.class));
        assertThat(inflatedViewFactory,is(DefaultInflatedViewFactory.class));
    }

    @Override
    public void injectFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    @Override
    public void injectInflatedViewFactory(InflatedViewFactory inflatedViewFactory) {
        this.inflatedViewFactory = inflatedViewFactory;
    }


}
