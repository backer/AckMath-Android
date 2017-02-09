package acker.brian.ackmath_android.challenge;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import acker.brian.ackmath_android.BaseActivity;
import acker.brian.ackmath_android.R;
import acker.brian.ackmath_android.databinding.ChallengeBinding;
import acker.brian.ackmath_android.event.LaunchScreenEvent;

/**
 * Created by Brian on 2/6/2017.
 */

public class ChallengeActivity extends BaseActivity {
    public static final String EXTRA_LOWER_BOUND = "extraLowerBound";
    public static final String EXTRA_UPPER_BOUND = "extraUpperBound";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChallengeBinding binding = DataBindingUtil.setContentView(this, R.layout.challenge);
        Intent intent = getIntent();
        if (intent == null) {
            // intent should never be null, but add a check just in case
            intent = new Intent();
        }
        ChallengeViewModel viewModel = new ChallengeViewModel(getResources(), intent.getIntExtra(EXTRA_LOWER_BOUND, 1),
                intent.getIntExtra(EXTRA_UPPER_BOUND, 100), binding.answer);
        binding.setViewModel(viewModel);
    }
}
