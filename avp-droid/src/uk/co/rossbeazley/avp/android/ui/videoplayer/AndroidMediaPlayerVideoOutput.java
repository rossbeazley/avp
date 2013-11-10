package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

public class AndroidMediaPlayerVideoOutput implements RenderedVideoOutput {

    @Override
    public void attachToViewGroup(ViewGroup container) {
        SurfaceView sv = createSurfaceView(container);
        addSurfaceViewToViewGroup(sv, container);
    }

    private void addSurfaceViewToViewGroup(SurfaceView sv, ViewGroup container) {
        ViewGroup.LayoutParams params = matchParentLayoutParams();
        container.addView(sv, params);
    }

    private SurfaceView createSurfaceView(ViewGroup container) {
        final Context context = container.getContext();
        SurfaceView sv = new SurfaceView(context);
        sv.getHolder().addCallback(new SurfaceHolderStateChangeCallbacks());
        return sv;
    }

    private ViewGroup.LayoutParams matchParentLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private static class SurfaceHolderStateChangeCallbacks implements SurfaceHolder.Callback {
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            //mediaPlayer.setDisplay(surfaceHolder);
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {        }
    }
}
