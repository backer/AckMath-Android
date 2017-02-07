package acker.brian.ackmath_android;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract;

import acker.brian.ackmath_android.databinding.FrontPageBinding;

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

}
