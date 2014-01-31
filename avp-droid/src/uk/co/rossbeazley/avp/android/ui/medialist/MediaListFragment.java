package uk.co.rossbeazley.avp.android.ui.medialist;

import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ScreenResourceIdFragment;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 31/01/2014
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class MediaListFragment  extends ScreenResourceIdFragment implements InjectableMediaListFragment{
    @Override
    protected int resourceId() {
        return R.layout.medialist;
    }
}
