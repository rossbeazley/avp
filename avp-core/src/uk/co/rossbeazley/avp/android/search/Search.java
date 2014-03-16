package uk.co.rossbeazley.avp.android.search;

public class Search {

    private final Query query;
    private Results results;


    /**
     * Guessing im going to have a results section, the results view can render this
     * or no results if empty list, do we need to have a pending flag or something?
     * maybe its a SearchResults object, no flag needed or this Search has SearchResults.
     *
     *
     * The results view nav controller should look out for the search_created event
     *
     * what happens with a pagenated result set?
     */

    public static Search fromString(String any_search_string) {
        return new Search(Query.fromString(any_search_string));
    }

    public static Search fromQuery(Query query) {
        return new Search(query);
    }

    private Search(Query query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Search search = (Search) o;

        if (!query.equals(search.query)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return query.hashCode();
    }

    public static Search fromResults(Results expectedResults) {
        Search search = new Search(null);
        search.results = expectedResults;
        return search;
    }

    public Results results() {
        return results;
    }
}
