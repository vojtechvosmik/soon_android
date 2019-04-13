package cz.vojtechvosmik.soon.utils

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