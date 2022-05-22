package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.util

import android.text.format.DateFormat
import java.util.*

fun formatDate(date: Date?): CharSequence {
    return if (date == null)
        ""
    else
        DateFormat.format("MMM dd yyyy", date)
}
fun formatDate(date: Calendar?): CharSequence {
    return if (date == null)
        ""
    else
        DateFormat.format("MMM dd yyyy", date)
}
