package cz.vojtechvosmik.soon.utils

import android.content.Context
import cz.vojtechvosmik.soon.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getRemainingDaysBeautiful(context: Context, days: Int): String {
        return when {
            days == 1 -> context.getString(R.string.tomorrow)
            days > 1 -> days.toString() + " " + context.getString(R.string.days)
            days == 0 -> context.getString(R.string.today)
            days == -1 -> context.getString(R.string.yesterday)
            days < -1 -> (days * -1).toString() + " " + context.getString(R.string.days_ago)
            else -> days.toString()
        }
    }

    fun getDateFromString(dateString: String, dateFormat: String): Date? {
        val format = SimpleDateFormat(dateFormat, Locale.getDefault())
        return try {
            format.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun getDatesDifferenceInDays(date: Date): Int {
        val difference = date.time - Date().time
        val seconds = difference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        return days.toInt() + 1
    }

    fun changeDateFormat(oldDate: Date, newFormatPattern: String): String? {
        val newFormat = SimpleDateFormat(newFormatPattern, Locale.getDefault())
        val date: String?
        return try {
            date = newFormat.format(oldDate)
            date
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }
}