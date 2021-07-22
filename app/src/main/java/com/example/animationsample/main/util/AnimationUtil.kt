package com.example.animationsample.main.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Animation

class AnimationUtil {
    fun getTranslateAnimator(
        view: View,
        translationValue: Float,
        duration: Long,
        repeatCount: Int,
        repeatMode: Int
    ): ObjectAnimator{
        val translationX =
            PropertyValuesHolder.ofFloat(View.TRANSLATION_X, translationValue, -translationValue)
        return ObjectAnimator.ofPropertyValuesHolder(view, translationX).apply {
            this.duration = duration
            this.repeatCount = repeatCount
            this.repeatMode = repeatMode
        }
    }

    fun getScalingAnimator(
        view: View,
        scaleFrom: Float,
        scaleTo: Float,
        duration: Long
    ): ValueAnimator{
        val scaleUpAnimator = ValueAnimator.ofFloat(scaleFrom, scaleTo)
        return scaleUpAnimator.apply {
            this.addUpdateListener { animation ->
                val newValue = animation?.animatedValue as Float
                view.scaleX = newValue
                view.scaleY = newValue
            }
            this.duration = duration
        }
    }

    fun getFadingAnimator(
        view: View,
        fromDegree: Float,
        toDegree: Float,
        duration: Long
    ): ObjectAnimator{
        val alpha =
            PropertyValuesHolder.ofFloat(View.ALPHA, fromDegree, toDegree)

        return ObjectAnimator.ofPropertyValuesHolder(view, alpha).apply {
            this.duration = duration
        }
    }

    fun getRotatingAnimator(
        view: View,
        fromDegree: Float,
        toDegree: Float,
        duration: Long
    ): ObjectAnimator{
        val rotation =
            PropertyValuesHolder.ofFloat(View.ROTATION, fromDegree, toDegree)
        return ObjectAnimator.ofPropertyValuesHolder(view, rotation).apply{
            this.duration = duration
        }
    }
}