package com.example.aicvirtualtour.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aicvirtualtour.R
import com.example.aicvirtualtour.databinding.ArtworkListItemBinding
import com.example.aicvirtualtour.models.ArtworkId

/**
 * ViewHolder for a RecyclerView list item on the ArtworkList/ArtworkSearch views
 */
class ArtworkIdViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.artwork_list_item, parent, false)) {

    private var title: TextView? = null

    init {
        title = itemView.findViewById(R.id.list_title)
    }

    fun bind(artworkId: ArtworkId) {
        title?.text = artworkId.title
    }
}

/**
 * Binds ArtworkIds to a ViewHolder in ArtworkListView
 */
class ArtworkListAdapter(private var items: List<ArtworkId>)
    : RecyclerView.Adapter<ArtworkIdViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkIdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArtworkListItemBinding.inflate(inflater)
        return ArtworkIdViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ArtworkIdViewHolder, position: Int) {
        val artworkId: ArtworkId = items[position]
        holder.bind(artworkId)
        // When an artwork is selected from the list, navigate to ArtworkView and pass its ID as a SafeArg
        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(ArtworkListFragmentDirections.actionArtworkListToArtwork(artworkId.id))
        }
    }

    fun setItems(newItems: List<ArtworkId>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

}