package cz.vojtechvosmik.soon.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.models.Event
import cz.vojtechvosmik.soon.room.AppDatabase
import cz.vojtechvosmik.soon.utils.DateUtils
import kotlinx.android.synthetic.main.activity_add_event.*
import java.util.*

class AddEventActivity : AppCompatActivity() {

    lateinit var datePicker: DatePickerDialog
    private var selectedDate: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        setupDatePicker()
        setupViews()
    }

    private fun setupViews() {
        img_back.setOnClickListener {
            onBackPressed()
        }
        edittxt_date.setOnClickListener {
            datePicker.show(supportFragmentManager, "DatePickerDialog")
        }
        fab_done.setOnClickListener {
            addEvent()
        }
    }

    private fun setupDatePicker() {
        val now = Calendar.getInstance()
        datePicker = DatePickerDialog.Builder(
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val dateString = ("$dayOfMonth.$monthOfYear.$year")
                val date = DateUtils.getDateFromString(dateString, "dd.MM.yyyy")
                selectedDate = date
                edittxt_date.setText(dateString)
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ).build()
        datePicker.isThemeDark = false
        datePicker.setHeaderColor(ContextCompat.getColor(this, R.color.green_main))
        datePicker.setAccentColor(ContextCompat.getColor(this, R.color.green_main))
    }

    private fun addEvent() {
        val title = edittxt_title.text.toString()
        if (!TextUtils.isEmpty(title) && selectedDate != null) {
            val event = Event(title = title, date = selectedDate!!)
            AppDatabase.getAppDatabase(this)?.eventsDao()?.insertEvent(event)
            finish()
        }else {
            //TODO insert from pls..
        }
    }
}
