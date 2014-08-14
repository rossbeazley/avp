package uk.co.rossbeazley.avp.android.ui.playout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.InflatedLayoutView;
import uk.co.rossbeazley.avp.android.ui.videoplayer.PlayerScreenAndroidView;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoOutputScreenPresenter;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public final class PlayoutFragment extends Fragment implements InjectableEventBus {

    public static final int VIDEOPLAYERSCREEN_LAYOUT_ID = R.layout.videoplayerscreen;

    private EventBus eventBus;

    @Override
    public void injectEventBus(EventBus eventBus)
    {
        this.eventBus = eventBus;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        InflatedLayoutView views = new InflatedLayoutView(inflater, container, VIDEOPLAYERSCREEN_LAYOUT_ID);
        PlayerScreenAndroidView playerScreenAndroidView = new PlayerScreenAndroidView(views);
        new VideoOutputScreenPresenter(playerScreenAndroidView, eventBus);
        View view = views.inflatedView();
        return view;
    }
}
