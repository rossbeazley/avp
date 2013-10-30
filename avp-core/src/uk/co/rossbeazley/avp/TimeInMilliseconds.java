package uk.co.rossbeazley.avp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        result = sdf.format(new Date(value));
        return result;
    }
}
