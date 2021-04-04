package com.example.aicvirtualtour.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aicvirtualtour.*
import com.example.aicvirtualtour.data.ResponseState
import com.example.aicvirtualtour.models.Department
import com.example.aicvirtualtour.viewModels.DepartmentListEvent.*
import com.example.aicvirtualtour.viewModels.DepartmentListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment acts as the "home page" for the app. It displays an alphabetical list of departments
 * and a search bar for keyword searches.
 * Selecting a department navigates to ArtworkListView which takes the department's name and id as
 * arguments and then displays a list of the works in that department.
 * Entering a keyword(s) in the search bar will navigate to ArtworkSearchView, which displays the list
 * of results for the search query.
 */
@AndroidEntryPoint
class DepartmentListFragment : Fragment() {

    private val viewModel: DepartmentListViewModel by viewModels()
    private lateinit var departmentList: RecyclerView
    private var listAdapter = DepartmentListAdapter(emptyList())

    private var searchBarDisplayed = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.department_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        departmentList = view.findViewById(R.id.departmentList)

        setupObservers()
        setupDepartmentList()
        setupSearchView()
        viewModel.performEvent(GetDepartments)
    }

    /**
     * Observes Result from view model call to repository and displays based on status
     */
    private fun setupObservers() {
        viewModel.responseState.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is ResponseState.Success<List<Department>> -> {
                    displayProgressBar(false)
                    listAdapter.setItems(response.data)
                }
                is ResponseState.Error ->
                    Toast.makeText(requireContext(), "Error receiving departments. Please try again later.", Toast.LENGTH_SHORT).show()
                is ResponseState.Loading ->
                    displayProgressBar(true)
            }
        })
    }

    /**
     * Bind data to RecyclerView elements with adapter and set layout
     */
    private fun setupDepartmentList() {
        departmentList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setupSearchView() {
        val searchButton = view?.findViewById<ImageButton>(R.id.searchButton)
        searchButton?.setOnClickListener { searchButtonTapped() }

        val searchBar = view?.findViewById<TextView>(R.id.searchBar)
        searchBar?.isVisible = searchBarDisplayed
        searchBar?.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_NEXT,
                EditorInfo.IME_ACTION_PREVIOUS,
                EditorInfo.IME_ACTION_DONE -> {
                    val enteredText = searchBar.text.toString()
                    if (enteredText.isNotBlank()) {
                        view?.findNavController()?.navigate(
                            // TODO: make this also navigate to artwork list and rework class to take different amounts of args
                            DepartmentListFragmentDirections.actionDepartmentListToSearchArtwork(
                                "", enteredText, true
                            )
                        )
                    }
                    true
                }
                else -> false
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

    private fun displayProgressBar(shouldShow: Boolean) {
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)
        progressBar?.visibility = if(shouldShow) View.VISIBLE else View.GONE
    }
}
