package uk.co.rossbeazley.avp.android.ui;

import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedViewFactory;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 30/01/2014
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public interface InjectableScreenResourceIdFragment {
    void setFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory);
    void setInflatedViewFactory(InflatedViewFactory inflatedViewFactory);
}