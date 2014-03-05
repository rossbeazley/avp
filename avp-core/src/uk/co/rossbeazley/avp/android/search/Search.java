package uk.co.rossbeazley.avp.android.search;

class Search {

    private final String query;


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

    public static Search fromQueryString(String any_search_string) {
        return new Search(any_search_string);
    }

    Search(String query) {
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
}
