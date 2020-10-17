package com.sohayb.miniprojet_bahisohayb.View

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sohayb.miniprojet_bahisohayb.R

class TopSpacingItemDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = padding
        view.setBackgroundResource(R.color.vomitoGiall)
    }

}
