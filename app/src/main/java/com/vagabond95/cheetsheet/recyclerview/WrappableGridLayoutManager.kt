package me.zepeto.group.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

/**
 * SpanCount 가 유동적으로 변했을 때
 * 크기를 조절해주는 LayoutManager
 * */
class WrappableGridLayoutManager(context: Context?, val preferedSpanCount: Int, @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL) :
    GridLayoutManager(context, preferedSpanCount, orientation, false) {

    private val mMeasuredDimension = IntArray(2)

    var measuredWidth = 0
    var measuredHeight = 0


    override fun onMeasure(recycler: RecyclerView.Recycler, state: RecyclerView.State, widthSpec: Int, heightSpec: Int) {
        // item 이 아직 추가되지 않았을 경우 예외처리
        if (itemCount == 0) {
            return
        }

        val suitableSpanCount = preferedSpanCount.coerceAtMost(itemCount)
        if (spanCount != suitableSpanCount) {
            spanCount = suitableSpanCount
            return
        }
        val widthMode = View.MeasureSpec.getMode(widthSpec)
        val heightMode = View.MeasureSpec.getMode(heightSpec)
        val widthSize = View.MeasureSpec.getSize(widthSpec)
        val heightSize = View.MeasureSpec.getSize(heightSpec)
        measuredWidth = 0
        measuredHeight = 0
        for (i in 0 until itemCount) {
            measureScrapChild(
                recycler, i,
                View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                mMeasuredDimension
            )
            if (orientation == HORIZONTAL) {
                if (i % spanCount == 0) {
                    measuredWidth += mMeasuredDimension[0]
                }
                if (i < spanCount) {
                    measuredHeight += mMeasuredDimension[1]
                }
            } else {
                if (i % spanCount == 0) {
                    measuredHeight += mMeasuredDimension[1]
                }
                if (i < spanCount) {
                    measuredWidth += mMeasuredDimension[0]
                }
            }
        }
        when (widthMode) {
            View.MeasureSpec.EXACTLY -> measuredWidth = widthSize
            View.MeasureSpec.AT_MOST, View.MeasureSpec.UNSPECIFIED -> {
            }
        }
        when (heightMode) {
            View.MeasureSpec.EXACTLY -> measuredHeight = heightSize
            View.MeasureSpec.AT_MOST, View.MeasureSpec.UNSPECIFIED -> {
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    private fun measureScrapChild(recycler: RecyclerView.Recycler, position: Int, widthSpec: Int, heightSpec: Int, measuredDimension: IntArray) {
        try {
            val view = recycler.getViewForPosition(position) ?: return
            val p = view.layoutParams as RecyclerView.LayoutParams
            val childWidthSpec = ViewGroup.getChildMeasureSpec(
                widthSpec,
                paddingLeft + paddingRight, p.width
            )
            val childHeightSpec = ViewGroup.getChildMeasureSpec(
                heightSpec,
                paddingTop + paddingBottom, p.height
            )
            view.measure(childWidthSpec, childHeightSpec)
            measuredDimension[0] = view.measuredWidth + p.leftMargin + p.rightMargin
            measuredDimension[1] = view.measuredHeight + p.bottomMargin + p.topMargin
            recycler.recycleView(view)
        } catch (e: Exception) {
        }
    }
}