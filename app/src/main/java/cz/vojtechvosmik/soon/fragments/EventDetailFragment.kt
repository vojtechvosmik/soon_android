package cz.vojtechvosmik.soon.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.activities.EditEventActivity
import cz.vojtechvosmik.soon.interfaces.EditEventInterface
import cz.vojtechvosmik.soon.models.Event
import cz.vojtechvosmik.soon.room.AppDatabase
import cz.vojtechvosmik.soon.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_event_detail.*
import java.lang.Exception

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
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        try {
            event = AppDatabase.getAppDatabase(context!!)!!.eventsDao().getEventsWithId(event!!.id!!)[0]
        }catch (e: Exception) {
            activity?.finish()
        }
    }

    private fun setupViews() {
        img_photo.setOnClickListener {
            if (fab_edit.visibility == View.VISIBLE) {
                fab_edit.visibility = View.INVISIBLE
            }else {
                fab_edit.visibility = View.VISIBLE
            }
        }
    }

    private fun fetchEvent() {
        txt_title.text = event!!.title
        txt_days_count.text = DateUtils.getRemainingDaysBeautiful(context!!, DateUtils.getDatesDifferenceInDays(event!!.date))
        img_photo.setImageBitmap(event!!.photo)
        fab_edit.setOnClickListener {
            val intent = Intent(context, EditEventActivity::class.java)
            EditEventActivity.editEventInterface = object: EditEventInterface {

                override fun onEventEdited(newEvent: Event) {
                    event = newEvent
                    fetchEvent()
                }
            }
            intent.putExtra("id", event!!.id)
            startActivity(intent)
        }
    }
}