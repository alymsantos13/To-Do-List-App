package ph.edu.dlsu.mobdeve.santos.alyssa.mc01

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.getTimeFormat
import android.util.Log
import androidx.annotation.RequiresApi
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.databinding.ActivityAddBinding
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model.Task
import ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util.formatDate
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val date: Calendar = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        overridePendingTransition(0, 0)
        setContentView(binding.root)

        createNotificationChannel()
        val dpd = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            date.set(year, month, dayOfMonth)
            binding.tvDate.text = formatDate(date)
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH))

        binding.btnDate.setOnClickListener {
            dpd.show()
        }
        binding.popupWindowButton.setOnClickListener {
            Log.d("HERE", "Main Activity")
            setResult(RESULT_OK, Intent(this, ListActivity::class.java).apply {
                putExtra(
                    TASK, Task(
                        null,
                        binding.etTitle1.text.toString(),
                        binding.etDescription.text.toString(),
                        Date(date.timeInMillis),
                        binding.cbRepeat1.isChecked,
                        false
                    )
                )
            })
            scheduleNotification()
            finish()
        }
    }

    private fun scheduleNotification() {
        val intent = Intent(applicationContext, Notification::class.java)
        val title = binding.etTitle1.text.toString()
        val message = binding.etDescription.text.toString()
        val repeating = binding.cbRepeat1.isChecked
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = date.timeInMillis - 86400000L
        if (repeating) {
            Log.d("HETO REPEATING BA?", "${repeating}")
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                time + 1000L,
                86400000L,
                pendingIntent
            )
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
            )
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A description of the channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val TASK = "TASK"
    }
}

