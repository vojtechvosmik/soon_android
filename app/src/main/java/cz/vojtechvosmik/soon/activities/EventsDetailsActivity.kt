package cz.vojtechvosmik.soon.activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.adapters.PagesAdapter
import cz.vojtechvosmik.soon.fragments.EventDetailFragment
import cz.vojtechvosmik.soon.room.AppDatabase
import kotlinx.android.synthetic.main.activity_events_details.*
import android.support.v4.view.ViewPager.OnPageChangeListener

class EventsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_details)
        setupViews()
        fetchEvents()
    }

    private fun setupViews() {
        progress.progressDrawable.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        pager_events.isSaveFromParentEnabled = false
        img_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getSelectedIndex(): Int {
        val extras = intent.extras
        return extras!!.getInt("index")
    }

    private fun fetchEvents() {
        var events = AppDatabase.getAppDatabase(this)?.eventsDao()?.getEvents()
        events = events?.sortedBy { it.date }
        val viewPagerAdapter = PagesAdapter(supportFragmentManager)
        events?.forEach { event ->
            val eventDetailFragment = EventDetailFragment.newInstance(event)
            viewPagerAdapter.addFragment(eventDetailFragment, event.title)
        }

        pager_events.adapter = viewPagerAdapter
        pager_events.currentItem = getSelectedIndex()
        pager_events.addOnPageChangeListener(object : OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                setPagerProgress(position, events!!.size)
            }

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })
        setPagerProgress(getSelectedIndex(), events!!.size)
    }

    private fun setPagerProgress(index: Int, eventsSize: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progress.setProgress(((index + 1) * (100 / eventsSize)), true)
        }else {
            progress.progress = ((index + 1) * (100 / eventsSize))
        }
    }
}
