package com.example.mywanandroid.ui.main.home.mybanner

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager


class MyBanner : FrameLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val mViewPager: ViewPager = ViewPager(context)
    private var canAutoScroll = true
    private lateinit var mHandler: Handler

    init {
        addView(
            mViewPager,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

    }


    fun setData(imageUrls: MutableList<String>) {
        imageUrls.add(0, imageUrls.last())
        imageUrls.add(imageUrls[1])
        val size = imageUrls.size

        mViewPager.adapter = MyBannerAdapter(imageUrls)

        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                mViewPager.currentItem += 1

                if (canAutoScroll) {
                    mHandler.sendEmptyMessageDelayed(0, 3000)
                }
            }
        }
        mViewPager.offscreenPageLimit = size
        mViewPager.setCurrentItem(1, false)
        mHandler.sendEmptyMessageDelayed(0, 3000)

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    mViewPager.setCurrentItem(size - 2, false)
                } else if (position == size - 1) {
                    mViewPager.setCurrentItem(1, false)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })



    }

}