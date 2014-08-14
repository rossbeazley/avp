package uk.co.rossbeazley.avp;

public final class UriString {
    public final String uri;

    private UriString(String uri) {
        this.uri = uri;
    }

    public static UriString from(String url) {
        return new UriString(url);
    }

    @Override
    public String toString() {
        return uri;
    }

    @Override
    public boolean equals(Object obj) {
        return uri.equals( ((UriString)obj).uri );
    }
}
