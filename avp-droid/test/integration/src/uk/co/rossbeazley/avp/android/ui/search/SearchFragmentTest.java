package uk.co.rossbeazley.avp.android.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public final class SearchFragmentTest implements InflatedViewFactory, FragmentScreenFactory {

    private int layoutId;

    private InflatedView viewFinder = new InflatedView() {

        @Override
        public View findViewById(int id) {
            return ANY_VIEW;
        }

        private View ANY_VIEW = null;

        @Override
        public View inflatedView() {
            return ANY_VIEW;
        }
    };

    private SearchFragment searchFragment;
    private CanFindViewById usedViewFinder;

    @Before
    public void setUp() throws Exception {
        searchFragment = new SearchFragment();
        searchFragment.injectInflatedViewFactory(this);
        searchFragment.injectFragmentScreenFactory(this);
    }


    @Test
    public void whenDisplayedUsesFactoryToCreateScreenWithHomeLayoutID() {
        searchFragment.onCreateView(null, null, null);
        assertThat(layoutId, is(R.layout.search));
    }

    @Test
    public void buildsScreenWithInflatedLayout() {
        searchFragment.onCreateView(null,null,null);
        assertThat((InflatedView) usedViewFinder, is(equalTo(viewFinder)));
    }

    @Override
    public InflatedView createInflatedView(LayoutInflater inflater, ViewGroup container, int layoutId) {
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
