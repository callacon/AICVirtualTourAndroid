package com.example.aicvirtualtour

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aicvirtualtour.databinding.ArtworkListItemBinding
import com.example.aicvirtualtour.fragments.ArtworkListFragmentDirections
import com.example.aicvirtualtour.models.ArtworkId

/**
 * ViewHolder for a RecyclerView list item on the ArtworkList/ArtworkSearch views
 */
class ArtworkIdViewHolder(private val binding: ArtworkListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ArtworkId) {
        with(binding) {
            listTitle.text = item.title
        }
    }
}

/**
 * Binds ArtworkIds to a ViewHolder in ArtworkListView
 */
class ArtworkListAdapter(private val list: List<ArtworkId>)
    : RecyclerView.Adapter<ArtworkIdViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkIdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArtworkListItemBinding.inflate(inflater)
        return ArtworkIdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtworkIdViewHolder, position: Int) {
        val artworkId: ArtworkId = list[position]
        holder.bind(artworkId)
        // When an artwork is selected from the list, navigate to ArtworkView and pass its ID as a SafeArg
        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(ArtworkListFragmentDirections.actionArtworkListToArtwork(artworkId.id))
        }
    }

    override fun getItemCount(): Int = list.size

}

/**
// * Binds ArtworkIds to a ViewHolder in ArtworkSearchView
// */
//class ArtworkSearchAdapter(private val list: List<ArtworkId>)
//    : RecyclerView.Adapter<ArtworkIdViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkIdViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return ArtworkIdViewHolder(inflater, parent)
//    }
//
//    override fun onBindViewHolder(holder: ArtworkIdViewHolder, position: Int) {
//        val artworkId: ArtworkId = list[position]
//        holder.bind(artworkId)
//        // When an artwork is selected from the list, navigate to ArtworkView and pass its ID as a SafeArg
//        holder.itemView.setOnClickListener{ view ->
//            view.findNavController().navigate(ArtworkSearchViewDirections.actionSearchArtworkToArtwork(artworkId.id))
//        }
//    }
//
//    override fun getItemCount(): Int = list.size
//
//}