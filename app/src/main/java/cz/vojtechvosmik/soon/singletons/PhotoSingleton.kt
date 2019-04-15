package cz.vojtechvosmik.soon.singletons

import android.graphics.Bitmap

object PhotoSingleton {

    var selectedPhoto: Bitmap? = null // not a best solution, but better than putting bitmap in intent extras :)
}