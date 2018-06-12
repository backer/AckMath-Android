package acker.brian.ackmath_android

import acker.brian.ackmath_android.challenge.ChallengeActivity
import acker.brian.ackmath_android.event.LaunchScreenEvent
import android.app.Activity
import android.content.Intent
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
                val intent = Intent(getApplicationContext(), ChallengeActivity::class.java)
                intent.putExtras(event.args)
                startActivity(intent)
            }
            else -> {
            }
        }
    }
}