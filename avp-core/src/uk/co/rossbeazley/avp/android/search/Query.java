package uk.co.rossbeazley.avp.android.search;

public class Query {

    private final String query;

    private Query(String query) {
        this.query = query;
    }

    public static Query fromString(String query) {
        return new Query(query);
    }

    public String string() {
        return query;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Query query1 = (Query) o;

        if (!query.equals(query1.query)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return query.hashCode();
    }
}
