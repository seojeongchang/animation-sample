package com.example.animationsample.main

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.CompoundButton
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import com.example.animationsample.R
import com.example.animationsample.databinding.ActivityMainBinding
import com.example.animationsample.main.common.Constants
import com.example.animationsample.main.presenter.MainPresenter
import com.example.animationsample.main.view.MainView

class MainActivity : AppCompatActivity(), MainView, View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivityMainBinding

    //presenter
    private lateinit var presenter: MainPresenter

    //drawer listener
    private lateinit var drawerListenerTranslate: DrawerLayout.DrawerListener
    private lateinit var drawerListenerScale: DrawerLayout.DrawerListener
    private lateinit var drawerListenerAlpha: DrawerLayout.DrawerListener
    private lateinit var drawerListenerRotate: DrawerLayout.DrawerListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        presenter = MainPresenter(this)
        //main view
        binding.btnTranslate.setOnClickListener(this)
        binding.btnScale.setOnClickListener(this)
        binding.btnAlpha.setOnClickListener(this)
        binding.btnRotate.setOnClickListener(this)
        binding.btnColor.setOnClickListener(this)
        //intent buttons
        binding.btnIntentTranslate.setOnClickListener(this)
        binding.btnIntentScale.setOnClickListener(this)
        binding.btnIntentAlpha.setOnClickListener(this)
        binding.btnIntentRotate.setOnClickListener(this)
        //drawer view
        binding.included.btnDrawerTranslate.setOnCheckedChangeListener(this)
        binding.included.btnDrawerScale.setOnCheckedChangeListener(this)
        binding.included.btnDrawerAlpha.setOnCheckedChangeListener(this)
        binding.included.btnDrawerRotate.setOnCheckedChangeListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            //main view
            binding.btnTranslate -> {
                presenter.translate(
                    binding.textView,
                    100f,
                    1000,
                    ValueAnimator.INFINITE,
                    ValueAnimator.REVERSE
                )
            }
            binding.btnScale -> {
                presenter.scale(
                    binding.textView,
                    1f,
                    2f,
                    1000
                )
            }
            binding.btnAlpha ->
                presenter.fade(
                    binding.textView,
                    0.5f,
                    1f,
                    1000
                )
            binding.btnRotate -> {
                presenter.rotate(
                    binding.textView,
                    0f,
                    360f,
                    1000
                )
            }
            binding.btnColor -> {
                presenter.changeColor(
                    binding.textView,
                    Color.parseColor("#FFFF0000"),
                    Color.parseColor("#FF0000FF"),
                    2000,
                    ObjectAnimator.INFINITE,
                    ObjectAnimator.REVERSE
                )
            }
            //intent buttons
            binding.btnIntentTranslate -> intentWithAnimation(Constants.TRANSLATE)
            binding.btnIntentScale -> intentWithAnimation(Constants.SCALE)
            binding.btnIntentAlpha -> intentWithAnimation(Constants.ALPHA)
            binding.btnIntentRotate -> intentWithAnimation(Constants.ROTATE)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView) {
            //drawer view
            binding.included.btnDrawerTranslate -> {
                if (isChecked) {
                    presenter.addDrawerListener(binding.drawerLayout, Constants.TRANSLATE)
                } else {
                    presenter.removeDrawerListener(binding.drawerLayout, drawerListenerTranslate)
                }
            }
            binding.included.btnDrawerScale -> {
                if (isChecked) {
                    scaleUpDownDrawer()
                } else {
                    presenter.removeDrawerListener(binding.drawerLayout, drawerListenerScale)
                }
            }
            binding.included.btnDrawerAlpha -> {
                if (isChecked) {
                    fadeInOutDrawer()
                } else {
                    presenter.removeDrawerListener(binding.drawerLayout, drawerListenerAlpha)
                }
            }
            binding.included.btnDrawerRotate -> {
                if (isChecked) {
                    rotateDrawer()
                } else {
                    presenter.removeDrawerListener(binding.drawerLayout, drawerListenerRotate)
                }
            }
        }
    }

    override fun cancelAnimator(anim: ObjectAnimator) {
        anim.cancel()
    }

    override fun translateTextView(anim: ObjectAnimator) {
        anim.start()
    }

    override fun scaleTextView(anim: ValueAnimator) {
        anim.start()
    }

    override fun FadeTextView(anim: ObjectAnimator) {
        anim.start()
    }

    override fun rotateTextView(anim: ObjectAnimator) {
        anim.start()
    }

    override fun changeTextViewColor(anim: ObjectAnimator) {
        anim.start()
    }

    override fun translateDrawer(drawerListener: DrawerLayout.DrawerListener) {
//        drawerListenerTranslate =
//            presenter.getDrawerListener(binding.contentView, Constants.TRANSLATE)
//        binding.drawerLayout.addDrawerListener(drawerListenerTranslate)
        binding.drawerLayout.addDrawerListener(drawerListener)
    }

    override fun scaleUpDownDrawer() {
        drawerListenerScale =
            presenter.getDrawerListener(binding.contentView, Constants.SCALE)
        binding.drawerLayout.addDrawerListener(drawerListenerScale)
    }

    override fun fadeInOutDrawer() {
        drawerListenerAlpha = presenter.getDrawerListener(binding.contentView, Constants.ALPHA)
        binding.drawerLayout.addDrawerListener(drawerListenerAlpha)
    }

    override fun rotateDrawer() {
        drawerListenerRotate = presenter.getDrawerListener(binding.contentView, Constants.ROTATE)
        binding.drawerLayout.addDrawerListener(drawerListenerRotate)
    }

    private fun intentWithAnimation(animationType: Int) {
        intent = Intent(this, SubActivity::class.java)
        startActivity(intent)

        when (animationType) {
            Constants.TRANSLATE -> overridePendingTransition(R.anim.translate, R.anim.hold)
            Constants.SCALE -> overridePendingTransition(R.anim.scale, R.anim.hold)
            Constants.ALPHA -> overridePendingTransition(R.anim.fadein, R.anim.hold)
            Constants.ROTATE -> overridePendingTransition(R.anim.rotate, R.anim.hold)
        }
    }
}