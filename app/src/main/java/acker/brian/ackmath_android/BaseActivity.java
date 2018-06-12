package acker.brian.ackmath_android;

import android.app.Activity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import acker.brian.ackmath_android.event.LaunchScreenEvent;


public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(LaunchScreenEvent event) {
        // empty onEvent method to prevent EventBusException in case child activity does not listen for events
    }
}
