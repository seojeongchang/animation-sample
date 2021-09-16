package com.example.animationsample.main.presenter

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.example.animationsample.main.common.Constants
import com.example.animationsample.main.util.AnimationUtil
import com.example.animationsample.main.view.MainView

class MainPresenter(view: MainView) {
    private var view = view
    private val animationUtil = AnimationUtil()
    private lateinit var drawerListener: DrawerLayout.DrawerListener

    //anim
    private var translatingAnimator: ObjectAnimator? = null
    private var scalingAnimtor: ValueAnimator? = null
    private var fadingAnimator: ObjectAnimator? = null
    private var rotatingAnimator: ObjectAnimator? = null
    private var coloringAnimator: ObjectAnimator? = null

    //flag
    private var onTranslating: Boolean = false
    private var onScaling: Boolean = false
    private var onFading: Boolean = false
    private var onRotating: Boolean = false
    private var onChangingColor: Boolean = false

    //ObjectAnimator
    fun translate(
        view: View,
        translationValue: Float,
        duration: Long,
        repeatCount: Int,
        repeatMode: Int
    ) {
        if (!onTranslating) {
            translatingAnimator = animationUtil.getTranslateAnimator(
                view,
                translationValue,
                duration,
                repeatCount,
                repeatMode
            )
            this.view.translateTextView(translatingAnimator!!)
        } else {
            this.view.cancelAnimator(translatingAnimator!!)
        }
        onTranslating = !onTranslating
    }

    //ValueAnimator
    fun scale(
        view: View,
        scaleFrom: Float,
        scaleTo: Float,
        duration: Long
    ) {
        if (!onScaling) {
            scalingAnimtor = animationUtil.getScalingAnimator(view, scaleFrom, scaleTo, duration)
        } else {
            scalingAnimtor = animationUtil.getScalingAnimator(view, scaleTo, scaleFrom, duration)
        }
        this.view.scaleTextView(scalingAnimtor!!)
        onScaling = !onScaling
    }

    //ObjectAnimator
    fun fade(
        view: View,
        fromDegree: Float,
        toDegree: Float,
        duration: Long
    ) {
        if (!onFading) {
            fadingAnimator = animationUtil.getFadingAnimator(view, fromDegree, toDegree, duration)
        } else {
            fadingAnimator = animationUtil.getFadingAnimator(view, toDegree, fromDegree, duration)
        }
        this.view.FadeTextView(fadingAnimator!!)
        onFading = !onFading
    }

    fun rotate(
        view: View,
        fromDegree: Float,
        toDegree: Float,
        duration: Long
    ) {
        if (!onRotating) {
            rotatingAnimator =
                animationUtil.getRotatingAnimator(view, fromDegree, toDegree, duration)
        } else {
            rotatingAnimator =
                animationUtil.getRotatingAnimator(view, toDegree, fromDegree, duration)
        }
        this.view.rotateTextView(rotatingAnimator!!)
        onRotating = !onRotating
    }

    fun changeColor(
        view: View,
        colorFrom: Int,
        colorTo: Int,
        duration: Long,
        repeatCount: Int,
        repeatMode: Int
    ) {
        if (!onChangingColor) {
            coloringAnimator =
                animationUtil.getColorChangingAnimator(
                    view,
                    colorFrom,
                    colorTo,
                    duration,
                    repeatCount,
                    repeatMode
                )
            this.view.changeTextViewColor(coloringAnimator!!)
        } else {
            this.view.cancelAnimator(coloringAnimator!!)
        }
        onChangingColor = !onChangingColor
    }

    fun addDrawerListener(contentView: View, listenerType: Int){
        val drawerListener = animationUtil.getDrawerListener(contentView, listenerType)
        this.view.translateDrawer(drawerListener)
    }

    // drawer Animation
    fun getDrawerListener(contentView: View, listenerType: Int): DrawerLayout.DrawerListener {
        when (listenerType) {
            Constants.TRANSLATE -> {
                drawerListener = object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                        contentView.translationX = drawerView.width * slideOffset
                    }

                    override fun onDrawerOpened(drawerView: View) {}

                    override fun onDrawerClosed(drawerView: View) {}

                    override fun onDrawerStateChanged(newState: Int) {}
                }
            }
            Constants.SCALE -> {
                drawerListener = object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
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

                    override fun onDrawerOpened(drawerView: View) {}

                    override fun onDrawerClosed(drawerView: View) {}

                    override fun onDrawerStateChanged(newState: Int) {}
                }
            }
            Constants.ALPHA -> {
                drawerListener = object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//                        getFadingOutAnimator(contentView, 1 - slideOffset, 0)
                    }

                    override fun onDrawerOpened(drawerView: View) {}

                    override fun onDrawerClosed(drawerView: View) {}

                    override fun onDrawerStateChanged(newState: Int) {}
                }
            }
            Constants.ROTATE -> {
                drawerListener = object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
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

                    override fun onDrawerOpened(drawerView: View) {}

                    override fun onDrawerClosed(drawerView: View) {}

                    override fun onDrawerStateChanged(newState: Int) {}
                }
            }
        }
        return drawerListener
    }

    fun removeDrawerListener(
        drawerLayout: DrawerLayout,
        drawerListener: DrawerLayout.DrawerListener
    ) {
        drawerLayout.removeDrawerListener(drawerListener)
    }
}