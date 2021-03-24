package com.example.aicvirtualtour

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder for a RecyclerView list item on the DepartmentListView
 */
class DepartmentViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var title: TextView? = null

    init {
        title = itemView.findViewById(R.id.list_title)
    }

    fun bind(department: Department) {
        title?.text = department.title
    }

}