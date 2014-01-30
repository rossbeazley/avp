package uk.co.rossbeazley.avp.android.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ui.InflatedLayoutView;
import uk.co.rossbeazley.avp.android.ui.InflatedViewFactory;

@RunWith(RobolectricTestRunner.class)
public class HomeFragmentTest implements InflatedViewFactory {

    @Test
    public void whenDisplayedUsesFactoryToCreateScreenWithHomeLayoutID() {

    }

    @Override
    public InflatedLayoutView createInflatedView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        return null;
    }
}
