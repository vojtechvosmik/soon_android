package cz.vojtechvosmik.soon.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.models.Event
import cz.vojtechvosmik.soon.room.AppDatabase
import cz.vojtechvosmik.soon.singletons.PhotoSingleton
import cz.vojtechvosmik.soon.utils.DateUtils
import kotlinx.android.synthetic.main.activity_add_event.*
import java.lang.Exception
import java.util.*

class EditEventActivity : AppCompatActivity() {

    private lateinit var datePicker: DatePickerDialog
    private var event: Event? = null
    private var selectedDate: Date? = null
    private var selectedPhoto: Bitmap? = null
    private val PHOTOS_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        setupDatePicker()
        setupViews()
        getEvent()
    }

    private fun setupViews() {
        img_back.setOnClickListener {
            onBackPressed()
        }
        edittxt_date.setOnClickListener {
            datePicker.show(supportFragmentManager, "DatePickerDialog")
        }
        fab_done.setOnClickListener {
            editEvent()
        }
        img_photo.setOnClickListener {
            startActivityForResult(Intent(this, PhotosActivity::class.java), PHOTOS_REQUEST_CODE)
        }
    }

    private fun setupDatePicker() {
        val now = Calendar.getInstance()
        datePicker = DatePickerDialog.Builder(
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val fixedMonthOfYear = monthOfYear + 1
                val dateString = ("$dayOfMonth.$fixedMonthOfYear.$year")
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

    private fun getEvent() {
        try {
            val id = intent.extras!!.getInt("id")
            val event = AppDatabase.getAppDatabase(this)!!.eventsDao().getEventsWithId(id)[0]
            this.event = event
            fetchEvent()
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun fetchEvent() {
        selectedDate = event!!.date
        selectedPhoto = event!!.photo
        edittxt_title.setText(event!!.title)
        edittxt_date.setText(DateUtils.changeDateFormat(event!!.date, "dd.MM.yyyy"))
        img_photo.setImageBitmap(event!!.photo)
    }

    private fun editEvent() {
        val title = edittxt_title.text.toString()
        if (!TextUtils.isEmpty(title) && selectedDate != null) {
            event!!.title = title
            event!!.date = selectedDate!!
            event!!.photo = selectedPhoto
            AppDatabase.getAppDatabase(this)?.eventsDao()?.updateEvent(event!!)
            finish()
        }else {
            //TODO insert from pls..
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PHOTOS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val photo = PhotoSingleton.selectedPhoto
            if (photo != null) {
                img_photo.setImageBitmap(photo)
                selectedPhoto = photo
                PhotoSingleton.selectedPhoto = null
            }
        }
    }
}
