package com.example.aicvirtualtour

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

/**
 * Binds Department data object to a ViewHolder in DepartmentListView
 */
class DepartmentListAdapter(private val list: List<Department>)
    : RecyclerView.Adapter<DepartmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DepartmentViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val department: Department = list[position]
        holder.bind(department)
        // When a department is selected, navigate to ArtworkListView and pass its ID and title as SafeArgs
        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(DepartmentListViewDirections.actionDepartmentListToArtworkList(department.id, department.title))
        }
    }

    override fun getItemCount(): Int = list.size

}

/**
 * Binds ArtworkIds to a ViewHolder in ArtworkListView
 */
class ArtworkListAdapter(private val list: List<ArtworkId>)
    : RecyclerView.Adapter<ArtworkIdViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkIdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArtworkIdViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ArtworkIdViewHolder, position: Int) {
        val artworkId: ArtworkId = list[position]
        holder.bind(artworkId)
        // When an artwork is selected from the list, navigate to ArtworkView and pass its ID as a SafeArg
        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(ArtworkListViewDirections.actionArtworkListToArtwork(artworkId.id))
        }
    }

    override fun getItemCount(): Int = list.size

}

/**
 * Binds ArtworkIds to a ViewHolder in ArtworkSearchView
 */
class ArtworkSearchAdapter(private val list: List<ArtworkId>)
    : RecyclerView.Adapter<ArtworkIdViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkIdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArtworkIdViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ArtworkIdViewHolder, position: Int) {
        val artworkId: ArtworkId = list[position]
        holder.bind(artworkId)
        // When an artwork is selected from the list, navigate to ArtworkView and pass its ID as a SafeArg
        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(ArtworkSearchViewDirections.actionSearchArtworkToArtwork(artworkId.id))
        }
    }

    override fun getItemCount(): Int = list.size

}