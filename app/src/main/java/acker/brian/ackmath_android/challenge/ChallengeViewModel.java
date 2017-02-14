package acker.brian.ackmath_android.challenge;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

import acker.brian.ackmath_android.BR;
import acker.brian.ackmath_android.R;
import acker.brian.ackmath_android.utils.TextUtils;

/**
 * Created by Brian on 2/6/2017.
 */

public class ChallengeViewModel extends BaseObservable {
    private static final int STATUS_UNANSWERED = 0;
    private static final int STATUS_INCORRECT = 1;
    private static final int STATUS_CORRECT = 2;

    private int lowerBound;
    private int upperBound;
    private int challengeNumber;
    private EditText answerText;
    private int answerStatus;
    private Resources resources;
    private long questionStartTime = 0;
    double answerTime = 0;

    public ChallengeViewModel(Resources resources, int lowerBound, int upperBound, EditText answer) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.answerText = answer;
        this.resources = resources;
        answerStatus = 0;
        randomizeChallengeNumber();
    }

    @Bindable
    public String getSuccessText() {
        switch (answerStatus) {
            case STATUS_CORRECT:
                return resources.getString(R.string.correct);
            case STATUS_INCORRECT:
                return resources.getString(R.string.incorrect);
            case STATUS_UNANSWERED:
            default:
                return "";
        }
    }

    @Bindable
    public String getChallengeText() {
        return "" + challengeNumber * challengeNumber;
    }

    @Bindable
    public int getSuccessVisibility() {
        return answerStatus == STATUS_CORRECT || answerStatus == STATUS_INCORRECT ? View.VISIBLE : View.INVISIBLE;
    }

    @Bindable
    public String getSuccessTimetext() {
        return resources.getString(R.string.answer_time, answerText, String.valueOf(answerTime));
    }

    @Bindable
    public String getButtonText() {
        switch (answerStatus) {
            case STATUS_CORRECT:
                return resources.getString(R.string.new_number);
            case STATUS_INCORRECT:
            case STATUS_UNANSWERED:
            default:
                return resources.getString(R.string.submit_answer);
        }
    }

    private void randomizeChallengeNumber() {
        Random random = new Random();
        challengeNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
        answerText.setText("");
        answerStatus = STATUS_UNANSWERED;
        notifyPropertyChanged(BR.challengeText);
        notifyPropertyChanged(BR.successText);
        notifyPropertyChanged(BR.successVisibility);
        notifyPropertyChanged(BR.buttonText);
        questionStartTime = SystemClock.elapsedRealtime();
    }

    public void onClickSubmit(View view) {
        if (!TextUtils.isEmpty(answerText.getText().toString())) {
            switch (answerStatus) {
                case STATUS_CORRECT:
                    randomizeChallengeNumber();
                    break;
                case STATUS_INCORRECT:
                case STATUS_UNANSWERED:
                default:
                    int answer = Integer.parseInt(answerText.getText().toString());
                    answerTime = ((SystemClock.elapsedRealtime() - questionStartTime) / 1000.0);
                    if (answer == challengeNumber) {
                        answerStatus = STATUS_CORRECT;
                    } else {
                        answerStatus = STATUS_INCORRECT;
                    }
                    notifyPropertyChanged(BR.successVisibility);
                    notifyPropertyChanged(BR.successText);
                    notifyPropertyChanged(BR.buttonText);
                    notifyPropertyChanged(BR.successTimetext);
                    break;
            }
        }
    }
}
