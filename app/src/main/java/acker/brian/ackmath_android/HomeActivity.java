package acker.brian.ackmath_android;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract;

import acker.brian.ackmath_android.challenge.ChallengeActivity;
import acker.brian.ackmath_android.databinding.FrontPageBinding;
import acker.brian.ackmath_android.event.LaunchScreenEvent;

/**
 * Created by Brian on 2/5/2017.
 */

public class HomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrontPageBinding binding = (FrontPageBinding) DataBindingUtil.setContentView(this, R.layout.front_page);
        FrontPageViewModel viewModel = new FrontPageViewModel(binding.editLower, binding.editUpper);
        binding.setViewModel(viewModel);
    }

    public void onEvent(LaunchScreenEvent event) {
        switch (event.getScreenType()) {
            case SQUARE_ROOT_CHALLENGE:
                Intent intent = new Intent(getApplicationContext(), ChallengeActivity.class);
                intent.putExtras(event.getArgs());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
