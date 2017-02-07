package acker.brian.ackmath_android.challenge;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import acker.brian.ackmath_android.utils.TextUtils;
import acker.brian.ackmath_android.BR;

/**
 * Created by Brian on 2/6/2017.
 */

public class ChallengeViewModel extends BaseObservable {
    private int lowerBound;
    private int upperBound;
    private int challengeNumber;
    private EditText answerText;
    private boolean showSuccess = false;

    public ChallengeViewModel(int lowerBound, int upperBound, EditText answer) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.answerText = answer;
        randomizeChallengeNumber();
    }

    @Bindable
    public String getChallengeText() {
        return "" + challengeNumber * challengeNumber;
    }

    @Bindable
    public int getSuccessVisibility() {
        return showSuccess ? View.VISIBLE : View.INVISIBLE;
    }

    private void randomizeChallengeNumber() {
        Random random = new Random();
        challengeNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
        notifyPropertyChanged(BR.challengeText);
    }

    public void onClickSubmit(View view) {
        if (!TextUtils.isEmpty(answerText.getText().toString())) {
            int answer = Integer.parseInt(answerText.getText().toString());
            if (answer == challengeNumber) {
                showSuccess = true;
                notifyPropertyChanged(BR.successVisibility);
            }
        }
    }
}
