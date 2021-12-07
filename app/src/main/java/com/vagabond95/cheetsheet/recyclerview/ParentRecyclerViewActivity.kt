package com.vagabond95.cheetsheet.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.vagabond95.cheetsheet.R
import com.vagabond95.cheetsheet.databinding.ActivityParentRecyclerViewBinding
import me.zepeto.group.view.WrappableGridLayoutManager

class ParentRecyclerViewActivity : AppCompatActivity() {

    private var _binding: ActivityParentRecyclerViewBinding? = null
    private val binding: ActivityParentRecyclerViewBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_recycler_view)

        val mainAdapter = MainRecyclerViewAdapter()
        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = WrappableGridLayoutManager(this@ParentRecyclerViewActivity, 3, RecyclerView.HORIZONTAL)
            PagerSnapHelper().attachToRecyclerView(this)
        }

        mainAdapter.items.addAll(
            listOf(
                MainModel(),
                MainModel(),
                MainModel(),
                MainModel(),
                MainModel(),
                MainModel(),
                MainModel(),
                MainModel(),
                MainModel(),
                MainModel(),
                MainModel(),
            )
        )
        mainAdapter.notifyDataSetChanged()
    }
}