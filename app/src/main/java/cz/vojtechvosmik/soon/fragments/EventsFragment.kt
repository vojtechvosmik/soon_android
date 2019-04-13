package cz.vojtechvosmik.soon.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.activities.AddEventActivity
import cz.vojtechvosmik.soon.models.Event
import cz.vojtechvosmik.soon.room.AppDatabase
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : BaseFragment() {

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_events
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        getEvents()
    }

    private fun setupViews() {
        fab_add_event.setOnClickListener {
            startActivity(Intent(context, AddEventActivity::class.java))
        }
    }

    private fun getEvents() {
        val events = AppDatabase.getAppDatabase(context!!)?.eventsDao()?.getEvents()
        if (events != null)
            fetchEvents(events)
    }

    private fun fetchEvents(events: List<Event>) {

    }
}