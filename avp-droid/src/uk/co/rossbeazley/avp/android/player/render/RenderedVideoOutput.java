package uk.co.rossbeazley.avp.android.player.render;

import android.view.ViewGroup;

public interface RenderedVideoOutput {     /* what if this was renamed to CanBeAttachedToViewGroup */
    void attachToViewGroup(ViewGroup container);
}
