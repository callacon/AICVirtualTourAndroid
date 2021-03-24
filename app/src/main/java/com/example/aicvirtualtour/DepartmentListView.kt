package com.example.aicvirtualtour

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DepartmentListView : Fragment(), CoroutineScope by MainScope() {

    private var departments: MutableList<Department> = mutableListOf()
    private var searchBarDisplayed = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.department_list_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchButton = view.findViewById<ImageButton>(R.id.searchButton)
        searchButton.setOnClickListener { searchButtonTapped() }

        val searchBar = view.findViewById<TextView>(R.id.searchBar)
        searchBar.isVisible = searchBarDisplayed
        searchBar.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_NEXT,
                EditorInfo.IME_ACTION_PREVIOUS,
                EditorInfo.IME_ACTION_DONE -> {
                    val enteredText = searchBar.text.toString()
                    if (!enteredText.isBlank()) {
                        view.findNavController().navigate(DepartmentListViewDirections.actionDepartmentListToSearchArtwork(enteredText))
                    }
                    true
                }
                else -> false
            }
        }

        launch(Dispatchers.Main) {
            try {
                val response = AICDataManager.apiClient.getDepartments()
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!.departments.let {
                        departments = it.toMutableList()
                        departments.sortBy { d -> d.title }
                    }
                    val departmentListView = view.findViewById(R.id.departmentListView) as RecyclerView
                    departmentListView.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = DepartmentListAdapter(departments)
                        addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
                    }
                }
            } catch (e: Exception) {
                Log.e("error:", e.message.toString())
            }
        }
    }

    private fun searchButtonTapped() {
        searchBarDisplayed = !searchBarDisplayed
        view?.findViewById<TextView>(R.id.searchBar)?.isVisible = searchBarDisplayed
    }

}