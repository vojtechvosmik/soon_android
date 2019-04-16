package cz.vojtechvosmik.soon.fragments

import android.os.Bundle
import android.view.View
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.models.Event
import cz.vojtechvosmik.soon.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_event_detail.*

class EventDetailFragment : BaseFragment() {

    private var event: Event? = null

    companion object {

        fun newInstance(event: Event): EventDetailFragment {
            val eventDetailFragment = EventDetailFragment()
            eventDetailFragment.event = event
            return eventDetailFragment
        }
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_event_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchEvent()
    }

    private fun fetchEvent() {
        if (event == null)
            return
        txt_title.text = event!!.title
        txt_days_count.text = ((DateUtils.getDatesDifferenceInDays(event!!.date) + 1).toString() + " " + context!!.getString(R.string.days))
        img_photo.setImageBitmap(event!!.photo)
    }
}