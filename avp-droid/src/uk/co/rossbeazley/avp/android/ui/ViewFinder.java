package uk.co.rossbeazley.avp.android.ui;

import android.view.View;
import android.widget.TextView;

public final class ViewFinder {

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

    public void clearOnClickListener(int id) {
        setOnClickListener(null,id); //wouldnt it be nice if there were no click listeners etc, just viewEvent. so click and longclick etc collapse into one interface but we still call setClickListener(<Listener>l)
    }

    public String getText(int id) {
        return String.valueOf(((TextView) canFindViewById.findViewById(id)).getText());
    }
}
