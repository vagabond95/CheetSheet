package com.vagabond95.cheetsheet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.vagabond95.cheetsheet.databinding.ActivityMainBinding
import com.vagabond95.cheetsheet.viewpager2.ParentPageActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {

        // 여기 item 추가
        val items = arrayListOf(
            MainItem("ViewPager2") { startViewPager2() }
        )

        val adapter = MainAdapter()
        recyclerView.adapter = adapter

        adapter.setItems(items)
    }

    private fun startViewPager2() {
        startActivity(Intent(this, ParentPageActivity::class.java))
    }
}