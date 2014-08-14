package uk.co.rossbeazley.avp.android.media;

import com.android.volley.Response;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class ReduxMediaRepositoryTest {

    private Results actualResults;
    private String REDUX_URL_FOR_ANY_QUERY ="https://i.bbcredux.com/asset/search?q=ANY_QUERY&token=VALIDTOKEN";
    private String unused_jsonForMediaItems = "{" +
            " \"results\": {" +
            "\"assets\":[" +
            " {\"name\":\"Item 1\" }," +
            " {\"name\":\"Item 2\" }," +
            " {\"name\":\"Item 3\" }" +
            "]} }";

    private JSONObject any_json_response = new JSONObject();

    @Test  @Ignore("TBD")
    public void theOneWhereItExecutesAQuerySuccessfully() {
        Query any_search = Query.fromString("ANY_QUERY");
        final List<MediaItem> mediaItems = Arrays.asList(new MediaItem("Item 1"),
                new MediaItem("Item 2"),
                new MediaItem("Item 3"));
        final Results expectedResults = new Results(mediaItems);

        RequestFactory requestFactory = new RequestFactory() {

            @Override
            public Request requestForQuery(String url, final Response.Listener<JSONObject> listener) {
                if(url.equals(REDUX_URL_FOR_ANY_QUERY)) {
                    return new Request() {
                        @Override
                        public void execute() {
                            listener.onResponse(any_json_response);
                        }
                    };
                }
                return null;
            }
        };

        ReduxUser authenticatedReduxUser = new ReduxUser() {
            @Override
            public AccessToken accessToken() {
                return new AccessToken("TOKEN");
            }
        };

        MediaItemFactory mediaItemFactory = new MediaItemFactory() {
            @Override
            public List<MediaItem> fromJson(JSONObject jsonObject) {
                return mediaItems;
            }
        };

        new ReduxMediaRepository(requestFactory,authenticatedReduxUser,mediaItemFactory)
                .execute(any_search, new MediaRepository.Success() {
                    @Override
                    public void call(Results results) {
                        actualResults = results;
                    }
                });

        assertThat(expectedResults,is(actualResults));
    }









    private final class ReduxMediaRepository implements MediaRepository {

        private final RequestFactory requestFactory;

        public ReduxMediaRepository(RequestFactory requestFactory, ReduxUser reduxUser, MediaItemFactory mediaItemFactory) {
            this.requestFactory = requestFactory;
        }

        @Override
        public void execute(Query search, Success success) {

        }
    }

    private interface MediaItemFactory {
        List<MediaItem> fromJson(JSONObject jsonObject);
    }

    private interface ReduxUser {
        AccessToken accessToken();
    }

    private interface RequestFactory {
        public Request requestForQuery(String url, Response.Listener<JSONObject> listener);
    }


    private interface Request {
        public void execute();
    }

    private final class AccessToken {
        private final String token;

        public AccessToken(String token) {
            this.token = token;
        }

        public String toString() {
            return token;
        }
    }
}
