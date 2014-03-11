package uk.co.rossbeazley.avp.android.ui.results;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.*;
import uk.co.rossbeazley.avp.android.ui.search.SearchFragment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ResultsFragmentTest implements InflatedViewFactory, FragmentScreenFactory {

    private int layoutId;


    //TODO remove duplication in tests, either through inheritance or by extracting Stubs eg FakeInflatedView
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

    private ResultsFragment fragment;
    private CanFindViewById usedViewFinder;

    @Before
    public void setUp() throws Exception {
        fragment = new ResultsFragment();
        fragment.injectInflatedViewFactory(this);
        fragment.injectFragmentScreenFactory(this);
    }


    @Test
    public void whenDisplayedUsesFactoryToCreateScreenWithHomeLayoutID() {
        fragment.onCreateView(null, null, null);
        assertThat(layoutId, is(R.layout.results));
    }

    @Test
    public void buildsScreenWithInflatedLayout() {
        fragment.onCreateView(null, null, null);
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
