package com.example.todoapproom

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class swaptodelete:ItemTouchHelper.Callback(){
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swapflag=ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0,swapflag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
}