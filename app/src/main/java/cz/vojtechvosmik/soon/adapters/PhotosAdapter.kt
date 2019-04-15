package cz.vojtechvosmik.soon.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.interfaces.PhotosInterface
import cz.vojtechvosmik.soon.utils.ScreenUtils
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosAdapter(private val context: Context, private val photos: ArrayList<Drawable>, private val photosInterface: PhotosInterface) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PhotoViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(context, itemView, photosInterface)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(viewHolder: PhotoViewHolder, position: Int) {
        viewHolder.setupViews(photos[position])
    }
}

class PhotoViewHolder(private val context: Context, itemView: View, private val photosInterface: PhotosInterface) : RecyclerView.ViewHolder(itemView) {

    private val container: RelativeLayout = itemView.layout_container
    private val imgPhoto: ImageView = itemView.img_photo

    fun setupViews(photo: Drawable) {
        val screenSize = ScreenUtils.getScreenSize(context)
        container.layoutParams.height = screenSize.width / 3
        container.layoutParams.width = screenSize.width / 3
        imgPhoto.setImageDrawable(photo)
        imgPhoto.setOnClickListener {
            photosInterface.onPhotoSelected(photo)
        }
    }
}