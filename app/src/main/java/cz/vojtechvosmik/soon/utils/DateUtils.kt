package cz.vojtechvosmik.soon.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateFromString(dateString: String, dateFormat: String) : Date? {
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
        return days.toInt()
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