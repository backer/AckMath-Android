package acker.brian.ackmath_android

import acker.brian.ackmath_android.event.LaunchScreenEvent
import android.app.Fragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

abstract class BaseFragment : Fragment() {

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    open fun onEvent(event: LaunchScreenEvent) {
        // provide onEvent method to prevent EventBusException in the case child fragment does not listen for events
    }
}