package cz.vojtechvosmik.soon.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.activities.AddEventActivity
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : BaseFragment() {

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_events
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        fab_add_event.setOnClickListener {
            startActivity(Intent(context, AddEventActivity::class.java))
        }
    }
}