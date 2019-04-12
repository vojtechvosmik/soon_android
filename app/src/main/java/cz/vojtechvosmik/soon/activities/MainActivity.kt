package cz.vojtechvosmik.soon.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.fragments.EventsFragment
import cz.vojtechvosmik.soon.utils.FragmentUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()
        FragmentUtils.changeFragment(EventsFragment(), false)
    }

    private fun setupFragment() {
        FragmentUtils.fragmentManager = supportFragmentManager
    }
}
