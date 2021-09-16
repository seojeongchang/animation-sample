package com.example.animationsample.main.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.drawerlayout.widget.DrawerLayout

interface MainView {
    fun translateTextView(anim: ObjectAnimator)
    fun scaleTextView(anim: ValueAnimator)
    fun FadeTextView(anim: ObjectAnimator)
    fun rotateTextView(anim: ObjectAnimator)
    fun changeTextViewColor(anim: ObjectAnimator)
    fun cancelAnimator(anim: ObjectAnimator)
//    fun cancelAnimator(anim: ValueAnimator)


    fun translateDrawer(drawerListener: DrawerLayout.DrawerListener)
    fun scaleUpDownDrawer()
    fun fadeInOutDrawer()
    fun rotateDrawer()
}