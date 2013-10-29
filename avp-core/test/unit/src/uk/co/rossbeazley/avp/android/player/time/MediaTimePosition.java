package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public class MediaTimePosition {
    private final TimeInMilliseconds currentPosition;
    private final TimeInMilliseconds totalLength;

    public MediaTimePosition(TimeInMilliseconds currentPosition, TimeInMilliseconds totalLength) {
        this.currentPosition = currentPosition;
        this.totalLength = totalLength;
    }


    private TimeInMilliseconds getCurrentPosition() {
        return currentPosition;
    }

    private TimeInMilliseconds getTotalLength() {
        return totalLength;
    }

    public boolean equals(Object o) {
        return ((MediaTimePosition) o).currentPosition.equals(currentPosition) &&
                ((MediaTimePosition) o).totalLength.equals(totalLength);
    }
}
