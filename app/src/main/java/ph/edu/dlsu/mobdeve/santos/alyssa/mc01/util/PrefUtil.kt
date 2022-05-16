package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util

import android.content.Context
import androidx.preference.PreferenceManager
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.TimerActivity

class PrefUtil {
    companion object{

        fun getTimerLength(context : Context) : Int {
            //Placeholder
            return 2
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "ph.edu.dlsu.mobdeve.santos.alyssa.timer.previous_timer_length"

        fun getPreviousTimerLengthSeconds(context : Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(seconds: Long, context : Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "ph.edu.dlsu.mobdeve.santos.alyssa.timer.timer_state"

        fun getTimerState(context:Context) : TimerActivity.TimerState {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return TimerActivity.TimerState.values()[ordinal]
        }

        fun setTimerState(state: TimerActivity.TimerState, context : Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID = "ph.edu.dlsu.mobdeve.santos.alyssa.timer.previous_timer_length"

        fun getSecondsRemaining(context : Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(seconds: Long, context : Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }
    }
}