package acker.brian.ackmath_android

import acker.brian.ackmath_android.challenge.ChallengeFragment
import acker.brian.ackmath_android.event.LaunchScreenEvent
import android.app.Activity
import android.os.Bundle
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, HomeFragment())
                .addToBackStack("root")
                .commit()
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: LaunchScreenEvent) {
        when (event.screenType) {
            LaunchScreenEvent.ScreenType.SQUARE_ROOT_CHALLENGE -> {
                val challengeFragment = ChallengeFragment()
                challengeFragment.arguments = event.args
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, challengeFragment)
                        .addToBackStack(challengeFragment.tag)
                        .commit()
            }
            else -> {
            }
        }
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}