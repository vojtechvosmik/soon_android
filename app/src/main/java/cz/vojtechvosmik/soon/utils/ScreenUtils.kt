package cz.vojtechvosmik.soon.utils

import android.content.Context
import android.view.WindowManager
import cz.vojtechvosmik.soon.models.ScreenSize
import android.util.DisplayMetrics

object ScreenUtils {

    fun getScreenSize(context: Context): ScreenSize {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val displayMetrics = DisplayMetrics()
        display.getMetrics(displayMetrics)
        return ScreenSize(
            displayMetrics.widthPixels,
            displayMetrics.heightPixels
        )
    }
}