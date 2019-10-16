package com.msf.shufflesongs.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class ItemDecoration(val spacing: Int, val spanCount: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(rect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        rect.left = spacing - column * spacing / spanCount
        rect.right = (column + 1) * spacing / spanCount

        if (position < spanCount) {
            rect.top = spacing
        }
        rect.bottom = spacing
    }
}