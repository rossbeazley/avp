package uk.co.rossbeazley.avp.android.media;


import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;

public interface MediaRepository {
    void execute(Query search, Success success);

    interface Success {
        void call(Results results);
    }
}
