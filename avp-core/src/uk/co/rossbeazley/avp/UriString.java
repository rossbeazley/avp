package uk.co.rossbeazley.avp;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 22/09/13
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public class UriString {
    public final String uri;

    public UriString(String uri) {
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
