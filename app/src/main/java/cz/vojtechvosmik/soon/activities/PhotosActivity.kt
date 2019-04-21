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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.Toast
import cz.vojtechvosmik.soon.singletons.PhotoSingleton
import kotlinx.android.synthetic.main.view_toolbar.view.*
import java.lang.Exception

class PhotosActivity : AppCompatActivity() {

    private val GALLERY_PHOTOS_REQUEST_CODE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        setupViews()
        setupToolbar()
        fetchPhotos()
    }

    private fun setupViews() {
        recycler_photos.layoutManager = GridLayoutManager(this, 3)
        fab_photo_library.setOnClickListener {
            selectPhotoFromGallery()
        }
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
                returnPhoto(photoBitmap)
            }
        })
    }

    private fun selectPhotoFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, getString(R.string.choose_photo)), GALLERY_PHOTOS_REQUEST_CODE)
    }

    private fun returnPhoto(photoBitmap: Bitmap) {
        PhotoSingleton.selectedPhoto = photoBitmap
        setResult(Activity.RESULT_OK, Intent())
        finish()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_PHOTOS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                val inputStream = contentResolver.openInputStream(data?.data!!)
                val options = BitmapFactory.Options()
                options.inSampleSize = 5
                val photoBitmap = BitmapFactory.decodeStream(inputStream, null, options)
                returnPhoto(photoBitmap!!)
            }catch (e: Exception) {
                Toast.makeText(this, getString(R.string.error_photo_from_gallery), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
