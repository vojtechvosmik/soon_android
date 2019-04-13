package cz.vojtechvosmik.soon.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.models.Event
import cz.vojtechvosmik.soon.utils.DateUtils
import kotlinx.android.synthetic.main.item_event.view.*

class EventsAdapter(private val context: Context, private val events: List<Event>) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): EventViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(context, itemView)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(viewHolder: EventViewHolder, position: Int) {
        viewHolder.setupViews(events[position])
    }
}

class EventViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val container: RelativeLayout = itemView.layout_container
    private val txtTitle: TextView = itemView.txt_title
    private val txtDaysCount: TextView = itemView.txt_days_count
    private val imgPhoto: ImageView = itemView.img_photo

    fun setupViews(event: Event) {
        txtTitle.text = event.title
        txtDaysCount.text = ((DateUtils.getDatesDifferenceInDays(event.date) + 1).toString() + " " + context.getString(R.string.days))

    }
}