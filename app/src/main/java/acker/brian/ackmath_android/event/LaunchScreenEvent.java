package acker.brian.ackmath_android.event;

import android.os.Bundle;

/**
 * Created by Brian on 2/6/2017.
 */

public class LaunchScreenEvent {

    public enum ScreenType {
        SQUARE_ROOT_CHALLENGE
    }

    private ScreenType screenType;
    private Bundle args;


    public LaunchScreenEvent(ScreenType screenType) {
        this.screenType = screenType;
        args = new Bundle();
    }

    public LaunchScreenEvent(ScreenType screenType, Bundle args) {
        this.screenType = screenType;
        this.args = args;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public Bundle getArgs() {
        return args;
    }
}
