package acker.brian.ackmath_android.challenge

import acker.brian.ackmath_android.BaseFragment
import acker.brian.ackmath_android.R
import acker.brian.ackmath_android.databinding.ChallengeBinding
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ChallengeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<ChallengeBinding>(inflater, R.layout.challenge, container, false)
        val lowerBound = arguments.getInt(LOWER_BOUND, 1)
        val upperBound = arguments.getInt(UPPER_BOUND, 100)
        val challengeViewModel = ChallengeViewModel(resources, lowerBound, upperBound, binding.answer)
        binding.viewModel = challengeViewModel
        return binding.root
    }

    companion object {
        const val LOWER_BOUND = "lowerBound"
        const val UPPER_BOUND = "upperBound"
    }
}