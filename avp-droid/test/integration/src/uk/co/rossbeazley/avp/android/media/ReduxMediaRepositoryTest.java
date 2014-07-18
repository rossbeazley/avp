package uk.co.rossbeazley.avp.android.media;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReduxMediaRepositoryTest {

    private Results actualResults;
    private String REDUX_URL_FOR_ANY_QUERY ="https://i.bbcredux.com/asset/search?q=ANY_QUERY&token=VALIDTOKEN";

    @Test
    public void theOneWhereItExecutesAQuerySuccessfully() {
        Query any_search = Query.fromString("ANY_QUERY");
        Results expectedResults = new Results(new MediaItem("Item 1"),
                new MediaItem("Item 2"),
                new MediaItem("Item 3"));

        RequestFactory requestFactory = new RequestFactory() {

            @Override
            public Request requestForQuery(String url, final Request.Success<JSONObject> listener) {
                if(url.equals(REDUX_URL_FOR_ANY_QUERY)) {
                    return new Request() {
                        @Override
                        public void execute() {
                            JSONObject jsonResponse = null;
                            try {

                                jsonResponse = new JSONObject("{" +
                                                            " \"results\": {" +
                                        "\"assets\":["+
                                        " {\"name\":\"Item 1\" },"+
                                        " {\"name\":\"Item 2\" },"+
                                        " {\"name\":\"Item 3\" }"+
                                                                "]} }"
                                );

                            } catch (JSONException e) {

                            }
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
        public Request requestForQuery(String url, Request.Success<JSONObject> listener);
    }


    private interface Request {

        /* this listener is potential double glazing*/
        public interface Success<T> {
            public void onResponse(T response);
        }

        public void execute();
    }
}
