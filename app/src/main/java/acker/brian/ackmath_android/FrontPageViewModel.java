package acker.brian.ackmath_android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import acker.brian.ackmath_android.challenge.ChallengeActivity;
import acker.brian.ackmath_android.event.LaunchScreenEvent;
import acker.brian.ackmath_android.utils.TextUtils;
import de.greenrobot.event.EventBus;

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
        if (!TextUtils.isEmpty(lowerBound.getText().toString()) && !TextUtils.isEmpty(upperBound.getText().toString())) {
            Bundle args = new Bundle();
            args.putInt(ChallengeActivity.EXTRA_LOWER_BOUND, Integer.parseInt(lowerBound.getText().toString()));
            args.putInt(ChallengeActivity.EXTRA_UPPER_BOUND, Integer.parseInt(upperBound.getText().toString()));
            EventBus.getDefault().post(new LaunchScreenEvent(LaunchScreenEvent.ScreenType.SQUARE_ROOT_CHALLENGE));
        }
    }
}
