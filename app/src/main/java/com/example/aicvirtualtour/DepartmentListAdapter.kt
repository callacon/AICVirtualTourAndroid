package com.example.aicvirtualtour

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aicvirtualtour.databinding.DepartmentListItemBinding
import com.example.aicvirtualtour.fragments.DepartmentListFragmentDirections
import com.example.aicvirtualtour.models.Department

/**
 * ViewHolder for a RecyclerView list item on the DepartmentListFragment
 */
class DepartmentViewHolder(private val binding: DepartmentListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Department) {
        with(binding) {
            listTitle.text = item.title
        }
    }
}

/**
 * Binds Department data object to a ViewHolder in DepartmentListFragment
 */
class DepartmentListAdapter(private val items: List<Department>)
    : RecyclerView.Adapter<DepartmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DepartmentListItemBinding.inflate(inflater)
        return DepartmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val department: Department = items[position]
        holder.bind(department)
        // When a department is selected, navigate to ArtworkListView and pass its ID and title as SafeArgs
        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(
                DepartmentListFragmentDirections.actionDepartmentListToArtworkList(department.id, department.title)
            )
        }
    }

    override fun getItemCount(): Int = items.size

}
