package uk.co.rossbeazley.avp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeInMilliseconds {
    final public long value;

    public TimeInMilliseconds(long i) {
        value=i;
    }

    @Override
    public boolean equals(Object obj) {
        return ((TimeInMilliseconds)obj).value==value;
    }

    public String asMinutesAndSeconds() {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        result = sdf.format(new Date(value));
        return result;
    }
}
