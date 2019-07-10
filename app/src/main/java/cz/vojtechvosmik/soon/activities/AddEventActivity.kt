package cz.vojtechvosmik.soon.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.widget.Toast
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.models.Event
import cz.vojtechvosmik.soon.room.AppDatabase
import cz.vojtechvosmik.soon.singletons.PhotoSingleton
import cz.vojtechvosmik.soon.utils.AnimationUtils
import cz.vojtechvosmik.soon.utils.DateUtils
import cz.vojtechvosmik.soon.utils.StorageUtils
import kotlinx.android.synthetic.main.activity_add_event.*
import java.lang.Exception
import java.util.*

class AddEventActivity : AppCompatActivity() {

    private lateinit var datePicker: DatePickerDialog
    private var selectedDate: Date? = null
    private var selectedPhoto: Bitmap? = null
    private val PHOTOS_REQUEST_CODE = 1

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
        img_photo.setOnClickListener {
            startActivityForResult(Intent(this, PhotosActivity::class.java), PHOTOS_REQUEST_CODE)
        }
        try {
            val firstPhoto = StorageUtils.getPhotosFromAssets(this)[0]
            img_photo.setImageDrawable(firstPhoto)
        }catch (e: Exception) {
            e.printStackTrace()
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

    private fun addEvent() {
        val title = edittxt_title.text.toString()
        if (!TextUtils.isEmpty(title) && selectedDate != null) {
            fab_done.setOnClickListener(null)
            if (selectedPhoto == null) {
                val photos = StorageUtils.getPhotosFromAssets(this)
                val defaultPhoto = (photos[0] as BitmapDrawable).bitmap
                selectedPhoto = defaultPhoto
            }
            val event = Event(
                title = title,
                date = selectedDate!!,
                photo = selectedPhoto
            )
            AppDatabase.getAppDatabase(this)?.eventsDao()?.insertEvent(event)
            finish()
        }else {
            if (TextUtils.isEmpty(title))
                onTitleFieldEmpty()
            if (selectedDate == null)
                onDateFieldEmpty()
        }
    }

    private fun onTitleFieldEmpty() {
        val shakeAnimation = AnimationUtils.getShakeAnimation(3f, 800)
        edittxt_title.startAnimation(shakeAnimation)
        Toast.makeText(this, getString(R.string.error_title_field_empty), Toast.LENGTH_SHORT).show()
    }

    private fun onDateFieldEmpty() {
        val shakeAnimation = AnimationUtils.getShakeAnimation(3f, 800)
        edittxt_date.startAnimation(shakeAnimation)
        Toast.makeText(this, getString(R.string.error_date_field_empty), Toast.LENGTH_SHORT).show()
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
