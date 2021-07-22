package com.example.animationsample.main.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator

interface MainView {
    fun translateTextView(anim: ObjectAnimator)
    fun scaleTextView(anim: ValueAnimator)
    fun FadeTextView(anim: ObjectAnimator)
    fun rotate()
    fun changeColor()
    fun cancelAnimator(anim: ObjectAnimator)


    fun translateDrawer()
    fun scaleUpDownDrawer()
    fun fadeInOutDrawer()
    fun rotateDrawer()
}