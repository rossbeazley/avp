package uk.co.rossbeazley.avp;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 09/09/13
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
public class TimeInMilliseconds {
    final public long value;

    public TimeInMilliseconds(long i) {
        value=i;
    }

    @Override
    public boolean equals(Object obj) {
        return ((TimeInMilliseconds)obj).value==value;
    }
}
