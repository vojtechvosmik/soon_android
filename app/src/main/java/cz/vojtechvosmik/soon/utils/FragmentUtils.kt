package cz.vojtechvosmik.soon.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import cz.vojtechvosmik.soon.R

object FragmentUtils {

    var fragmentManager: FragmentManager? = null

    fun changeFragment(fragment: Fragment, backStack: Boolean) {
        val fragmentManager = fragmentManager
        if (fragmentManager != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            //val topFragment = fragmentManager.findFragmentById(R.id.fragment_container)
            //if (topFragment != null)
            //    fragmentTransaction.remove(topFragment)
            fragmentTransaction.add(R.id.fragment_container, fragment)
            if (backStack)
                fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}