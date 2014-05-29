package uk.co.rossbeazley.avp.android.ui.results;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import uk.co.rossbeazley.avp.android.R;

public class ResultsListItemView extends RelativeLayout {


    private TextView programmeTextView;

    public ResultsListItemView(Context context) {
        super(context);
    }

    public ResultsListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResultsListItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bindProgrammeTextView();
    }

    private void bindProgrammeTextView() {
        this.programmeTextView = (TextView)findViewById(R.id.results_list_item_programme_text);
    }

    public void setProgrammeText(String one) {
        this.programmeTextView.setText(one);
    }
}
