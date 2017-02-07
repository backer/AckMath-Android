package acker.brian.ackmath_android;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
        Log.v("BINDING", "Begin button clicked!");
    }
}
