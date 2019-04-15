package cz.vojtechvosmik.soon.activities

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.adapters.PhotosAdapter
import cz.vojtechvosmik.soon.interfaces.PhotosInterface
import cz.vojtechvosmik.soon.utils.StorageUtils
import kotlinx.android.synthetic.main.activity_photos.*
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.view.View
import cz.vojtechvosmik.soon.singletons.PhotoSingleton
import kotlinx.android.synthetic.main.view_toolbar.view.*

class PhotosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        setupViews()
        setupToolbar()
        fetchPhotos()
    }

    private fun setupViews() {
        recycler_photos.layoutManager = GridLayoutManager(this, 3)
    }

    private fun setupToolbar() {
        toolbar.img_logo.visibility = View.INVISIBLE
        toolbar.img_back.visibility = View.VISIBLE
        toolbar.img_back.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, Intent())
            finish()
        }
    }

    private fun fetchPhotos() {
        val photos = StorageUtils.getPhotosFromAssets(this)
        recycler_photos.adapter = PhotosAdapter(this, photos, object: PhotosInterface {

            override fun onPhotoSelected(photo: Drawable) {
                val photoBitmap = (photo as BitmapDrawable).bitmap
                PhotoSingleton.selectedPhoto = photoBitmap
                setResult(Activity.RESULT_OK, Intent())
                finish()
            }
        })
    }
}
