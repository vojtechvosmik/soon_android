package cz.vojtechvosmik.soon.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog
import cz.vojtechvosmik.soon.R
import kotlinx.android.synthetic.main.activity_add_event.*
import java.util.*

class AddEventActivity : AppCompatActivity() {

    lateinit var datePicker: DatePickerDialog

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
        //edittxt_date.setOnClickListener {
            datePicker.show(supportFragmentManager, "DatePickerDialog")
        //}
    }

    private fun setupDatePicker() {
        val now = Calendar.getInstance()
        datePicker = DatePickerDialog.Builder(
            DatePickerDialog.OnDateSetListener { dialog, year, monthOfYear, dayOfMonth ->
                edittxt_date.setText(("$dayOfMonth.$monthOfYear.$year"))
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ).build()
        datePicker.isThemeDark = false
        datePicker.setHeaderColor(ContextCompat.getColor(this, R.color.green_main))
        datePicker.setAccentColor(ContextCompat.getColor(this, R.color.green_main))
    }
}
