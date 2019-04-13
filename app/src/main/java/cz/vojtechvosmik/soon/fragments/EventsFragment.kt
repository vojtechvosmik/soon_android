package cz.vojtechvosmik.soon.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.activities.AddEventActivity
import cz.vojtechvosmik.soon.adapters.EventsAdapter
import cz.vojtechvosmik.soon.room.AppDatabase
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : BaseFragment() {

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_events
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fetchEvents()
    }

    override fun onResume() {
        super.onResume()
        fetchEvents()
    }

    private fun setupViews() {
        recycler_events.layoutManager = LinearLayoutManager(context)
        fab_add_event.setOnClickListener {
            startActivity(Intent(context, AddEventActivity::class.java))
        }
    }

    private fun fetchEvents() {
        recycler_events.adapter = null
        val events = AppDatabase.getAppDatabase(context!!)?.eventsDao()?.getEvents()
        if (events != null)
            recycler_events.adapter = EventsAdapter(context!!, events)
    }
}