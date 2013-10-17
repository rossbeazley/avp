package uk.co.rossbeazley.redux.android.videoplayer;

import uk.co.rossbeazley.avp.android.videoplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.videoplayer.VideoView;

public interface MediaPlayerViewFactory {
    VideoView createVideoView(MediaPlayer mediaPlayer);
}
