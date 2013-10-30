package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public class MediaTimePosition {
    private final TimeInMilliseconds currentPosition;
    private final TimeInMilliseconds totalLength;

    public MediaTimePosition(TimeInMilliseconds currentPosition, TimeInMilliseconds totalLength) {
        this.currentPosition = currentPosition;
        this.totalLength = totalLength;
    }


    public TimeInMilliseconds getCurrentPosition() {
        return currentPosition;
    }

    public TimeInMilliseconds getTotalLength() {
        return totalLength;
    }

    public boolean equals(Object o) {
        MediaTimePosition other = (MediaTimePosition) o;
        return checkCurrentPosition(other) && checkTotalLength(other);
    }

    private boolean checkTotalLength(MediaTimePosition other) {
        return totalLength.equals(other.totalLength);
    }

    private boolean checkCurrentPosition(MediaTimePosition other) {
        return currentPosition.equals(other.currentPosition);
    }
}
