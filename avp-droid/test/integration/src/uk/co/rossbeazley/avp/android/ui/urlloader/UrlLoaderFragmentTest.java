package uk.co.rossbeazley.avp.android.ui.urlloader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.*;
import uk.co.rossbeazley.avp.android.ui.urloader.UrlLoaderFragment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UrlLoaderFragmentTest implements InflatedViewFactory, FragmentScreenFactory {

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

    private UrlLoaderFragment urlLoaderFragment;
    private CanFindViewById usedViewFinder;

    @Before
    public void setUp() throws Exception {
        urlLoaderFragment = new UrlLoaderFragment();
        urlLoaderFragment.injectInflatedViewFactory(this);
        urlLoaderFragment.injectFragmentScreenFactory(this);
    }


    @Test
    public void whenDisplayedUsesFactoryToCreateScreenWithHomeLayoutID() {
        urlLoaderFragment.onCreateView(null, null, null);
        assertThat("wrong layout id, expected R.layout.urlloader",layoutId, is(R.layout.urlloader));
    }

    @Test
    public void buildsScreenWithInflatedLayout() {
        urlLoaderFragment.onCreateView(null, null, null);
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
