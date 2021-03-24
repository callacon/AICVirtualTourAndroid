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
 * This Fragment acts as the "home page" for the app. It displays an alphabetical list of departments
 * and a search bar for keyword searches.
 * Selecting a department navigates to ArtworkListView which takes the department's name and id as
 * arguments and then displays a list of the works in that department.
 * Entering a keyword(s) in the search bar will navigate to ArtworkSearchView, which displays the list
 * of results for the search query.
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
                        // Save list of departments (in alphabetical order)
                        departments = it.toMutableList()
                        departments.sortBy { d -> d.title }
                    }
                    val departmentListView = view.findViewById(R.id.departmentListView) as RecyclerView
                    // Bind data to RecyclerView elements with adapter and set layout
                    departmentListView.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = DepartmentListAdapter(departments)
                        addItemDecoration(
                            DividerItemDecoration(
                                this.context,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("error:", e.message.toString())
            }
        }
    }

    /**
     * Hide/show the search bar when the search icon (magnifying glass) is tapped.
     */
    private fun searchButtonTapped() {
        searchBarDisplayed = !searchBarDisplayed
        view?.findViewById<TextView>(R.id.searchBar)?.isVisible = searchBarDisplayed
    }

}