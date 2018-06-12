package acker.brian.ackmath_android

import acker.brian.ackmath_android.databinding.FrontPageBinding
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding : FrontPageBinding =
                DataBindingUtil.inflate<FrontPageBinding>(inflater, R.layout.front_page, container, false)
        val viewModel = FrontPageViewModel(binding.editLower, binding.editUpper)
        binding.viewModel = viewModel
        return binding.root
    }
}