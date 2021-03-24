package com.example.aicvirtualtour

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArtworkIdViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var title: TextView? = null


    init {
        title = itemView.findViewById(R.id.list_title)
    }

    fun bind(artworkId: ArtworkId) {
        title?.text = artworkId.title
    }

}