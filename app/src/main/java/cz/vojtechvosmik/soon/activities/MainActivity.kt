package cz.vojtechvosmik.soon.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cz.vojtechvosmik.soon.R
import cz.vojtechvosmik.soon.fragments.EventsFragment
import cz.vojtechvosmik.soon.utils.FragmentUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSplash()
        setupFragment()
        FragmentUtils.changeFragment(EventsFragment(), false)
    }

    private fun setupFragment() {
        FragmentUtils.fragmentManager = supportFragmentManager
    }

    private fun showSplash() {
        startActivity(Intent(this, SplashActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
    }
}
