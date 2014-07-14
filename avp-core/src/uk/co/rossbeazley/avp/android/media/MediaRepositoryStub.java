package uk.co.rossbeazley.avp.android.media;

import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;

import java.util.HashMap;
import java.util.Map;

public class MediaRepositoryStub implements MediaRepository {
    private final Map<Query, Results> resultsByQuery;

    public MediaRepositoryStub(Map<Query, Results> resultsByQuery) {
        this.resultsByQuery = resultsByQuery;
    }

    public static MediaRepositoryStub createMediaRepository() {
        return new MediaRepositoryStub(new HashMap<Query, Results>(){{
            put(Query.fromString("ross"),
                    new Results(new MediaItem("Item 1"),
                                new MediaItem("Item 2"),
                                new MediaItem("Item 3"),
                                new MediaItem("Item 4"),
                                new MediaItem("Item 5"),
                                new MediaItem("Item 6")));
        }});
    }

    @Override
    public void execute(Query search, Success success) {
        success.call(resultsByQuery.get(search));
    }
}
