package acker.brian.ackmath_android.event

import android.os.Bundle

/**
 * Created by Brian on 2/6/2017.
 */

class LaunchScreenEvent {

    val screenType: ScreenType
    val args: Bundle

    enum class ScreenType {
        SQUARE_ROOT_CHALLENGE
    }

    constructor(screenType: ScreenType, args: Bundle) {
        this.screenType = screenType
        this.args = args
    }
}
