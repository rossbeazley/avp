package uk.co.rossbeazley.avp.android.media;

public class MediaItem {
    private final String programTitle;

    public MediaItem(String programTitle) {

        this.programTitle = programTitle;
    }

    public String titleString() {
        return programTitle;
    }
}
