package uk.co.rossbeazley.avp.android.ui.home;


import org.junit.Test;
import uk.co.rossbeazley.avp.android.ui.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HomeFragmentInjectorTest implements InjectableHomeFragment {

    private FragmentScreenFactory fragmentScreenFactory;
    private InflatedViewFactory inflatedViewFactory;

    @Test
    public void inject() {

        HomeFragmentInjector homeFragmentInjector = new HomeFragmentInjector();
        homeFragmentInjector.inject(this);

        assertThat(fragmentScreenFactory, is(HomeFragmentScreenFactory.class));
        assertThat(inflatedViewFactory,is(DefaultInflatedViewFactory.class));
    }

    @Override
    public void setFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    @Override
    public void setInflatedViewFactory(InflatedViewFactory inflatedViewFactory) {
        this.inflatedViewFactory = inflatedViewFactory;
    }


}
