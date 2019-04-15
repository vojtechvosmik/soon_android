package cz.vojtechvosmik.soon.adapters

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cz.vojtechvosmik.soon.R
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter(private val context: Context, private val photos: List<Bitmap>) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PhotoViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(context, itemView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(viewHolder: PhotoViewHolder, position: Int) {
        viewHolder.setupViews(photos[position])
    }
}

class PhotoViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imgPhoto: ImageView = itemView.img_photo

    fun setupViews(photo: Bitmap) {
        imgPhoto.setImageBitmap(photo)
    }
}