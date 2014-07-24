package uk.co.rossbeazley.avp;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 25/09/13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class Events {
    public static final String APP_SHUTDOWN = "app_shutdown";

    public static final String PLAYER_PLAYING = "player_playing";
    public static final String PLAYER_PAUSED = "player_paused";
    public static final String USER_PAUSE = "pause";
    public static final String USER_PLAY = "play";
    public static final String USER_SCRUB = "scrub";
    public static final String USER_EXIT_VIDEO_SCREEN = "exit_video_Screen";
    public static final String PLAYER_TIME_UPDATE = "mediaTimeUpdate";
    public static final String PLAYER_VIEW_CREATED = "player_view_created";
    public static final String PERFORMING_QUERY = "search_created";
    public static final String USER_WANTS_TO_GOTO_SEARCH = "user_wants to_goto_search";

    public static final String APP_START = "app_start";
    public static final String APP_HIDDEN = "app_hidden";
    public static final String APP_RESUMED = "app_resumed";
}
