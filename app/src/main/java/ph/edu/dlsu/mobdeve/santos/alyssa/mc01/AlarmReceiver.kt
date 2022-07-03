package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.NotificationUtils
import java.util.*

class AlarmReceiver() : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    //Handles the scheduling and alarm of the tasks
    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getLongExtra(ID_KEY, 0)
        val title = intent.getStringExtra(TITLE_KEY)
        val message = intent.getStringExtra(MESSAGE_KEY)

        Log.d("RECEIVED", id.toString())

        val notificationUtils = NotificationUtils(context)
        val manager = notificationUtils.getManager()
        val notification = notificationUtils.getNotificationBuilder(title, message).build()
        manager.notify(id.hashCode(), notification)

        val count = manager.activeNotifications.size
        val summary = notificationUtils.getNotificationBuilder()
            .setGroupSummary(true)
            .setNumber(count)
            .build()
        manager.notify(0, summary)
    }

    fun cancelAlarm(context: Context, task: Task) {
        updateAlarm(context, task, false)
    }

    fun setAlarm(context: Context, task: Task) {
        updateAlarm(context, task, true)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun updateAlarm(context: Context, task: Task, willSave: Boolean) {
        val id = task._id

        val broadcastIntent = PendingIntent.getBroadcast(
            context,
            id.hashCode(),
            Intent(context, AlarmReceiver::class.java).apply {
                putExtra(ID_KEY, task._id)
                putExtra(TITLE_KEY, task.name)
                putExtra(MESSAGE_KEY, task.description)
            },
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        with(context.getSystemService(Context.ALARM_SERVICE) as AlarmManager) {
            if (willSave) {
                val time = task.dueDate!!.time - 86400000L + 5000

                if (task.repeat) {
                    setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        Calendar.getInstance().timeInMillis + 5000,
                        86400000L,
                        broadcastIntent
                    )
                } else {
                    setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        time,
                        broadcastIntent
                    )
                }
            } else {
                cancel(broadcastIntent)
            }
        }
    }

    companion object {
        const val ID_KEY = "ID_KEY"
        const val TITLE_KEY = "titleExtra"
        const val MESSAGE_KEY = "messageExtra"
    }
}