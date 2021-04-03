package com.example.aicvirtualtour.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aicvirtualtour.*
import com.example.aicvirtualtour.application.ViewModelModule
import com.example.aicvirtualtour.application.injectViewModel
import com.example.aicvirtualtour.data.AutoClearedValue
import com.example.aicvirtualtour.data.Result
import com.example.aicvirtualtour.databinding.DepartmentListBinding
import com.example.aicvirtualtour.helper.autoCleared
import com.example.aicvirtualtour.viewModels.DepartmentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

    private val binding: DepartmentListBinding by AutoClearedValue()
    private val viewModel: DepartmentListViewModel by viewModels()
    private lateinit var adapter: DepartmentListAdapter

    private var searchBarDisplayed = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.department_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupDepartmentList()
        setupSearchView()
    }

    /**
     * Bind data to RecyclerView elements with adapter and set layout
     */
    private fun setupDepartmentList() {
        binding.departmentList.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    /**
     * Observes Result from view model call to repository and displays based on status
     */
    private fun setupObservers() {
        viewModel.departments.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    adapter = DepartmentListAdapter(it.data!!)
                }
                Result.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Result.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
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
                    if (!enteredText.isBlank()) {
                        view?.findNavController()?.navigate(
                            // TODO: make this also navigate to artwork list and rework class to take different amounts of args
                            DepartmentListFragmentDirections.actionDepartmentListToSearchArtwork(
                                enteredText
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

}
