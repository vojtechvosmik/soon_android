package cz.vojtechvosmik.soon.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import cz.vojtechvosmik.soon.R

abstract class BaseFragment : Fragment() {

    protected abstract fun getFragmentLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getFragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BaseFragment", "fragment view created")
    }

    //override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
    //    return if (enter) AnimationUtils.loadAnimation(activity, R.anim.anim_fade_in) else AnimationUtils.loadAnimation(activity, R.anim.anim_fade_out)
    //}
}