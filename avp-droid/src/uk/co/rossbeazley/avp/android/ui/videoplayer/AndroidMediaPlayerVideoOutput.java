package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.mediaplayer.CanAttachToAndroidView;

public class AndroidMediaPlayerVideoOutput implements RenderedVideoOutput {

    private SurfaceHolderStateChangeCallbacks surfaceHolderStateChangeCallbacks;

    AndroidMediaPlayerVideoOutput(CanAttachToAndroidView mediaPlayer) {
        surfaceHolderStateChangeCallbacks = new SurfaceHolderStateChangeCallbacks(mediaPlayer);
    }

    @Override
    public void attachToViewGroup(ViewGroup container) {
        SurfaceView sv = createSurfaceViewFromViewGroupContext(container);
        addSurfaceViewToViewGroup(sv, container);
    }

    private SurfaceView createSurfaceViewFromViewGroupContext(ViewGroup container) {
        final Context context = container.getContext();
        SurfaceView sv = new SurfaceView(context);
        sv.getHolder().addCallback(surfaceHolderStateChangeCallbacks);
        return sv;
    }

    private void addSurfaceViewToViewGroup(SurfaceView sv, ViewGroup container) {
        ViewGroup.LayoutParams params = matchParentLayoutParams();
        container.addView(sv, params);
    }

    private ViewGroup.LayoutParams matchParentLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }



    private static class SurfaceHolderStateChangeCallbacks implements SurfaceHolder.Callback {
        private final CanAttachToAndroidView mediaPlayer;

        public SurfaceHolderStateChangeCallbacks(CanAttachToAndroidView mediaPlayer) {
            this.mediaPlayer = mediaPlayer;
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            mediaPlayer.setDisplay(surfaceHolder);
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {        }
    }
}
