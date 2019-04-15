package cz.vojtechvosmik.soon.utils

import android.content.Context
import android.graphics.drawable.Drawable

object StorageUtils {

    fun getPhotosFromAssets(context: Context): ArrayList<Drawable> {
        var imagesNames = context.assets.list("photos")
        if (imagesNames == null)
            imagesNames = ArrayList<String>().toTypedArray()
        val images = ArrayList<Drawable>()
        imagesNames.forEach { image ->
            val inputStream = context.assets.open("photos/$image")
            val drawable = Drawable.createFromStream(inputStream, null)
            images.add(drawable)
        }
        return images
    }
}