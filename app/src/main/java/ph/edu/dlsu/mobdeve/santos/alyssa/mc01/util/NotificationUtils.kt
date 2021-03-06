package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.ListActivity
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.R

class NotificationUtils(base: Context) : ContextWrapper(base) {

    //Handles the showing of Notification
    private var manager: NotificationManager? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }

    // Create channel for Android version 26+
    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)

        getManager().createNotificationChannel(channel)
    }

    // Get Manager
    fun getManager(): NotificationManager {
        if (manager == null) manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }

    fun getNotificationBuilder(title: String? = "Some title", message: String? = "You have new reminders."): NotificationCompat.Builder  {
        val intent = Intent(this, ListActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title)
            .setContentText(message)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setGroup(GROUP_KEY)
    }

    companion object {
        const val CHANNEL_ID =  "TODO_APP_ID"
        const val CHANNEL_NAME = "TODO_APP"
        const val GROUP_KEY = "TODO_GROUP_KEY"
    }
}