package com.vagabond95.cheetsheet.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vagabond95.cheetsheet.R
import com.vagabond95.cheetsheet.databinding.ActivityParentPageBinding

class ParentPageActivity : AppCompatActivity() {

    private var _binding: ActivityParentPageBinding? = null
    private val binding: ActivityParentPageBinding get() = _binding!!

    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_page)
        initPagerWithTab()
    }

    private fun initPagerWithTab() = with(binding) {
        val adapter = PageAdapter(this@ParentPageActivity)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1 // 보이지 않는 page 를 언제 로드할지. limit = 1 일 경우 양옆에 '1개' 미리 로드
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // ViewPage 미리 세팅 후 설정
        tabLayoutMediator = TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, pos ->
            tab.text = pos.toString()
        }.apply {
            attach()
        }
    }
}