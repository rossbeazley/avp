package uk.co.rossbeazley.avp.android.ui.urlloader;


import org.junit.Test;
import uk.co.rossbeazley.avp.android.application.ApplicationServices;
import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.android.application.DependencyInjectionFrameworkFactory;
import uk.co.rossbeazley.avp.android.application.NullApplicationServices;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.urloader.InjectableUrlLoaderFragment;
import uk.co.rossbeazley.avp.android.ui.urloader.UrlLoaderFragmentInjector;
import uk.co.rossbeazley.avp.android.ui.urloader.UrlLoaderFragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UrlLoaderFragmentInjectorTest implements InjectableUrlLoaderFragment {

    private FragmentScreenFactory fragmentScreenFactory;

    @Test
    public void inject() {

        EventBus UNUSED_EVENT_BUS = null;
        UrlLoaderFragmentInjector urlLoaderFragmentInjector = new UrlLoaderFragmentInjector(UNUSED_EVENT_BUS);
        urlLoaderFragmentInjector.inject(this);

        assertThat(fragmentScreenFactory, is(UrlLoaderFragmentScreenFactory.class));
    }

    @Test
    public void injectIntegratedTest() {
        ApplicationServices services = new NullApplicationServices();
        DependenciesService ds = new DependencyInjectionFrameworkFactory().createDependencyInjectionFramework(services);

        ds.injectDependencies(this);

        assertThat(fragmentScreenFactory, is(UrlLoaderFragmentScreenFactory.class));
    }

    @Override
    public void injectFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

}
