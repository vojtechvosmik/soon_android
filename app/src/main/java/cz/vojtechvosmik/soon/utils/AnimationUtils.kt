package cz.vojtechvosmik.soon.utils

import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation

object AnimationUtils {

    fun getShakeAnimation(cycles: Float, duration: Long): TranslateAnimation {
        val shake = TranslateAnimation(0f, 15f, 0f, 0f)
        shake.duration = duration
        shake.interpolator = CycleInterpolator(cycles)
        return shake
    }
}