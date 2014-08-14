package uk.co.rossbeazley.avp.android.tests;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public final class ActivityForTesting extends Activity {
    public LinearLayout LL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.LL = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        this.LL.setLayoutParams(params);
        setContentView(LL);
    }
}
