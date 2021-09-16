package com.example.animationsample.main.util

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.animationsample.main.common.Constants

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

    fun getColorChangingAnimator(
        view: View,
        colorFrom: Int,
        colorTo: Int,
        duration: Long,
        repeatCount: Int,
        repeatMode: Int
    ):ObjectAnimator{
        val textColor =
            PropertyValuesHolder.ofInt("textColor", colorFrom, colorTo).apply {
                this.setEvaluator(ArgbEvaluator())
            }
        return ObjectAnimator.ofPropertyValuesHolder(view, textColor).apply {
            this.duration = duration
            this.repeatCount = repeatCount
            this.repeatMode = repeatMode
        }
    }

    fun getDrawerListener(
        contentView: View,
        listenerType: Int
    ): DrawerLayout.DrawerListener{
        return object : DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                when(listenerType){
                    Constants.TRANSLATE -> {
                        contentView.translationX = drawerView.width * slideOffset
                    }
                    Constants.SCALE -> {
                        //scale the view based on current slide offset
                        val offsetScale = 1 - slideOffset
                        contentView.scaleX = offsetScale
                        contentView.scaleY = offsetScale
                        //Translate the view, accounting for the scaled width
                        val xOffset = drawerView.width * slideOffset
                        val xOffsetDiff = contentView.width * slideOffset
                        val xTranslation = xOffsetDiff - xOffset
                        contentView.translationX = xTranslation
                    }
                    Constants.ALPHA -> {

                    }
                    Constants.ROTATE -> {
                        //scale the view based on current slide offset
                        val offsetScale = 1 - slideOffset
                        contentView.scaleX = offsetScale
                        contentView.scaleY = offsetScale
                        //Translate the view, accounting for the scaled width
                        val xOffset = drawerView.width * slideOffset
                        val xOffsetDiff = contentView.width * slideOffset
                        val rotationValue = 360 * slideOffset
                        val xTranslation = xOffsetDiff - xOffset
                        contentView.translationX = xTranslation
                        contentView.rotation = rotationValue
                        Log.d("ddd", "slideOffset = $slideOffset / rotationValue = $rotationValue")
                    }
                }
            }

            override fun onDrawerOpened(drawerView: View) {

            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerStateChanged(newState: Int) {

            }
        }
    }
}