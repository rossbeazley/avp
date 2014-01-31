package uk.co.rossbeazley.avp.android.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedViewFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class HomeFragmentTest implements InflatedViewFactory, FragmentScreenFactory {

    private int layoutId;

    private CanFindViewById viewFinder = new CanFindViewById() {

        @Override
        public View findViewById(int id) {
            return ANY_VIEW;
        }

        private View ANY_VIEW = null;
    };

    private HomeFragment homeFragment;
    private CanFindViewById usedViewFinder;

    @Before
    public void setUp() throws Exception {
        homeFragment = new HomeFragment();
        homeFragment.setInflatedViewFactory(this);
        homeFragment.setFragmentScreenFactory(this);
    }


    @Test
    public void whenDisplayedUsesFactoryToCreateScreenWithHomeLayoutID() {
        homeFragment.onCreateView(null, null, null);
        assertThat(layoutId, is(R.layout.home));
    }

    @Test
    public void buildsScreenWithInflatedLayout() {
        homeFragment.onCreateView(null,null,null);
        assertThat(usedViewFinder, is(viewFinder));
    }

    @Override
    public CanFindViewById createInflatedView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        // assert the correct use of inflater and container?
        this.layoutId = layoutId;
        return this.viewFinder;
    }

    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        this.usedViewFinder = inflatedLayoutView;
        return null;
    }
}
