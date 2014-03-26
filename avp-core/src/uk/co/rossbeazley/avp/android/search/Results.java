package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.Programme;

import java.util.Arrays;
import java.util.List;

public class Results {

    private final Programme[] programmes;

    public Results(Programme... programmes) {
        this.programmes = programmes;
    }

    public List<String> names() {
        return Arrays.asList("A result");
    }

    public int size() {
        return 1;
    }
}
