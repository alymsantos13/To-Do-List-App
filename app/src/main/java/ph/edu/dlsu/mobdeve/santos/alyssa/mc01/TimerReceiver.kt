package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.PrefUtil

class TimerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        PrefUtil.setTimerState(TimerActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}