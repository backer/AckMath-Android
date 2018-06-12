package acker.brian.ackmath_android;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import acker.brian.ackmath_android.challenge.ChallengeFragment;
import acker.brian.ackmath_android.event.LaunchScreenEvent;
import acker.brian.ackmath_android.utils.TextUtils;

/**
 * Created by Brian on 2/5/2017.
 */

public class FrontPageViewModel {
    EditText lowerBound;
    EditText upperBound;

    public FrontPageViewModel(EditText lowerBound, EditText upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public void onClickBegin(View view) {
        if (!TextUtils.isEmpty(lowerBound.getText().toString())
                && !TextUtils.isEmpty(upperBound.getText().toString())) {
            Bundle args = new Bundle();
            int lower = Integer.parseInt(lowerBound.getText().toString());
            int upper = Integer.parseInt(upperBound.getText().toString());
            if (validateBounds(lower, upper)) {
                args.putInt(ChallengeFragment.LOWER_BOUND, lower);
                args.putInt(ChallengeFragment.UPPER_BOUND, upper);
                EventBus.getDefault().post(new LaunchScreenEvent(LaunchScreenEvent.ScreenType.SQUARE_ROOT_CHALLENGE,
                        args));
            }
        }
    }

    public boolean validateBounds(int lower, int upper) {
        return upper > 0 && lower > 0 && upper - lower > 0;
    }
}
