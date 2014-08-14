package uk.co.rossbeazley.avp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class TimeInMilliseconds {
    final public long value;
    private final SimpleDateFormat sdf;

    private TimeInMilliseconds(long i) {
        value=i;
        sdf = new SimpleDateFormat("mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static TimeInMilliseconds fromLong(long i) {
        return new TimeInMilliseconds(i);
    }

    @Override
    public boolean equals(Object obj) {
        return ((TimeInMilliseconds)obj).value==value;
    }

    public String asMinutesAndSeconds() {
        String result = "";
        result = sdf.format(new Date(value));
        return result;
    }

    @Override
    public String toString() {
        return asMinutesAndSeconds();
    }

    public static TimeInMilliseconds fromInt(int millisecondsAsInt) {
        return new TimeInMilliseconds(millisecondsAsInt);
    }
}
