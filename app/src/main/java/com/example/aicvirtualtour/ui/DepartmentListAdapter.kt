package com.example.aicvirtualtour.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aicvirtualtour.R
import com.example.aicvirtualtour.models.Department

/**
 * ViewHolder for a RecyclerView list item on the DepartmentListFragment
 */
class DepartmentViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.department_list_item, parent, false)) {

    private var title: TextView? = null

    init {
        title = itemView.findViewById(R.id.list_title)
    }

    fun bind(department: Department) {
        title?.text = department.title
    }

}

/**
 * Binds Department data object to a ViewHolder in DepartmentListFragment
 */
class DepartmentListAdapter(private var items: List<Department>)
    : RecyclerView.Adapter<DepartmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DepartmentViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val department: Department = items[position]
        holder.bind(department)
        // When a department is selected, navigate to ArtworkListView and pass its ID and title as SafeArgs
        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(
                DepartmentListFragmentDirections.actionDepartmentListToArtworkList(department.id, department.title, false)
            )
        }
    }

    fun setItems(newItems: List<Department>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

}
