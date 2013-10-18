package uk.co.rossbeazley.avp.android.ui;

import android.view.View;
import android.widget.TextView;

public class ViewFinder {

    private final CanFindViewById canFindViewById;

    public ViewFinder(CanFindViewById canFindViewById) {
        this.canFindViewById = canFindViewById;
    }

    public View find(int id) {
        return canFindViewById.findViewById(id);
    }

    public void setVisibility(int visibility, int id) {
        canFindViewById.findViewById(id).setVisibility(visibility);
    }

    public void setText(String text, int id) {
        ((TextView) canFindViewById.findViewById(id)).setText(text);
    }

    public void setOnClickListener(View.OnClickListener onClickListener, int id) {
        canFindViewById.findViewById(id).setOnClickListener(onClickListener);
    }
}