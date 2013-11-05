package uk.co.rossbeazley.avp;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimeInMillisecondsAsHoursMinsTest {

    @Test
    public void oneMinuteTwenty() {
        TimeInMilliseconds time = TimeInMilliseconds.fromLong(80000);
        String convertedMilliseconds = time.asMinutesAndSeconds();
        String ONE_MIN_TWENTY = "01:20";
        assertThat(convertedMilliseconds,is(ONE_MIN_TWENTY));
    }
}
