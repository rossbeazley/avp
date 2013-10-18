package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.Toast;

public class AndroidMediaPlayerVideoOutput implements RenderedVideoOutput {



    @Override
    public void attachToViewGroup(ViewGroup container) {

        final Context context = container.getContext();

        SurfaceView sv = new SurfaceView(context);

        ViewGroup.LayoutParams params = matchParentLayoutParams();

        container.addView(sv,params);

        SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Toast.makeText(context, "Surface created", Toast.LENGTH_LONG).show();
                //mediaPlayer.setDisplay(surfaceHolder);
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
                Toast.makeText(context, "Surface changed", Toast.LENGTH_LONG).show();
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Toast.makeText(context, "Surface destroyed", Toast.LENGTH_LONG).show();
            }
        };

        sv.getHolder().addCallback(callback);

    }


    private ViewGroup.LayoutParams matchParentLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
