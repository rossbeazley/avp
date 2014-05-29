package uk.co.rossbeazley.avp.android.media;

import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;

import java.util.Map;

public class MediaRepositoryStub implements MediaRepository {
    private final Map<Query, Results> resultsByQuery;

    public MediaRepositoryStub(Map<Query, Results> resultsByQuery) {
        this.resultsByQuery = resultsByQuery;
    }

    @Override
    public Results execute(Query search) {
        waitTwoSeconds();
        return resultsByQuery.get(search);
    }

    private void waitTwoSeconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
