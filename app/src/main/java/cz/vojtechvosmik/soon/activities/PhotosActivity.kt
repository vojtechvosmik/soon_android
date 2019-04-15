package cz.vojtechvosmik.soon.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.utils.StorageUtils

class PhotosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        fetchPhotos()
    }

    private fun fetchPhotos() {
        val photos = StorageUtils.getPhotosFromAssets(this)

    }
}
