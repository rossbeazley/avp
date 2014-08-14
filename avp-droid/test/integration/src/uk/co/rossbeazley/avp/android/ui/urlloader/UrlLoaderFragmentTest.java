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
public final class UrlLoaderFragmentTest implements InflatedViewFactory, FragmentScreenFactory {

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

    private ScreenResourceIdFragment urlLoaderFragment;
    private CanFindViewById usedViewFinder;

    @Before
    public void setUp() throws Exception {
        urlLoaderFragment = createFragment();
        urlLoaderFragment.injectInflatedViewFactory(this);
        urlLoaderFragment.injectFragmentScreenFactory(this);
    }

    //specialised
    private ScreenResourceIdFragment createFragment() {
        return new UrlLoaderFragment();
    }


    @Test //generic
    public void whenDisplayedUsesFactoryToCreateScreenWithHomeLayoutID() {
        urlLoaderFragment.onCreateView(null, null, null);
        assertThat("wrong layout id, expected R.layout.urlloader",layoutId, is(expectedLayoutID()));
    }

    //specialised
    private int expectedLayoutID() {
        return R.layout.urlloader;
    }

    @Test //generic
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
