package acker.brian.ackmath_android

import acker.brian.ackmath_android.challenge.ChallengeFragment
import acker.brian.ackmath_android.event.LaunchScreenEvent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import org.greenrobot.eventbus.EventBus

/**
 * Created by Brian on 2/5/2017.
 */

class FrontPageViewModel(private val lowerBound: EditText, private val upperBound: EditText) {

    fun onClickBegin(view: View) {
        if (!TextUtils.isEmpty(lowerBound.text.toString()) && !TextUtils.isEmpty(upperBound.text.toString())) {
            val args = Bundle()
            val lower = Integer.parseInt(lowerBound.text.toString())
            val upper = Integer.parseInt(upperBound.text.toString())
            if (validateBounds(lower, upper)) {
                args.putInt(ChallengeFragment.LOWER_BOUND, lower)
                args.putInt(ChallengeFragment.UPPER_BOUND, upper)
                EventBus.getDefault().post(LaunchScreenEvent(LaunchScreenEvent.ScreenType.SQUARE_ROOT_CHALLENGE, args))
            }
        }
    }

    fun validateBounds(lower: Int, upper: Int): Boolean {
        return upper > 0 && lower > 0 && upper - lower > 0
    }
}
