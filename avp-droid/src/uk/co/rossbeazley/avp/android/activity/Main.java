package uk.co.rossbeazley.avp.android.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.application.ActivityWirer;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class Main extends Activity implements WireableMain {

    private EventBus eventBus;
    private IntentToEventDispatcher intentParser;
    private Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentParser.onIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        intentParser.onIntent(getIntent());
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if(fragment instanceof VideoPlayerFragment) {
            ((VideoPlayerFragment) fragment).setVideoPlayerFragmentScreenFactory(new VideoPlayerFragmentScreenFactory(eventBus));
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        eventBus.announce(Events.APP_HIDDEN);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.announce(Events.APP_SHUTDOWN);
    }





    @Override
    public void wire(ActivityWirer activityWirer) {
        activityWirer.wire(this); //TODO remove double dispatch
    }

    @Override
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void setIntentParser(IntentToEventDispatcher intentParser) {
        this.intentParser = intentParser;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
