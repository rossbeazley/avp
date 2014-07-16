package uk.co.rossbeazley.avp.android.media;

import com.android.volley.Response;
import org.json.JSONArray;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReduxMediaRepositoryTest {

    private Results actualResults;
    private String REDUX_URL_FOR_ANY_QUERY = "";

    @Test
    public void theOneWhereItExecutesAQuerySuccessfully() {
        Query any_search = Query.fromString("ANY_QUERY");
        Results expectedResults = new Results(new MediaItem("Item 1"),
                new MediaItem("Item 2"),
                new MediaItem("Item 3"));

        RequestFactory requestFactory = new RequestFactory() {

            @Override
            public Request requestForQuery(String url, final Request.Success<JSONArray> listener) {
                if(url.equals(REDUX_URL_FOR_ANY_QUERY)) {
                    return new Request() {
                        @Override
                        public void execute() {
                            JSONArray jsonResponse = new JSONArray();
                            listener.onResponse(jsonResponse);
                        }
                    };
                }

                return null;
            }
        };

        new ReduxMediaRepository(requestFactory)
                .execute(any_search, new MediaRepository.Success() {
                    @Override
                    public void call(Results results) {
                        actualResults = results;
                    }
                });
        assertThat(expectedResults,is(actualResults));
    }

    private class ReduxMediaRepository implements MediaRepository {

        private final RequestFactory requestFactory;

        public ReduxMediaRepository(RequestFactory requestFactory) {
            this.requestFactory = requestFactory;
        }

        @Override
        public void execute(Query search, Success success) {

        }
    }




    private interface RequestFactory {
        public Request requestForQuery(String url, Request.Success<JSONArray> listener);
    }


    private interface Request {

        /* this listener is potential double glazing*/
        public interface Success<T> {
            public void onResponse(T response);
        }

        public void execute();
    }
}
