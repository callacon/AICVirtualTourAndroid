package com.example.aicvirtualtour.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aicvirtualtour.data.AICRepository
import com.example.aicvirtualtour.data.ResponseState
import com.example.aicvirtualtour.ui.ArtworkListEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworkListViewModel @Inject constructor(
    private val repository: AICRepository
) : ViewModel() {

    private val _responseState: MutableLiveData<ResponseState<Set<Any>>> = MutableLiveData()
    val responseState: LiveData<ResponseState<Set<Any>>>
        get() = _responseState

    var currentPage = 1
    var departmentId: String? = null
    var searchQuery: String? = null

    fun performEvent(artworkListEvent: ArtworkListEvent) {
        viewModelScope.launch {
            when(artworkListEvent) {
                is GetArtworksInDepartment -> {
                    if (!departmentId.isNullOrEmpty()) {
                        repository.getArtworksInDepartment(departmentId!!, currentPage).onEach { responseState ->
                            _responseState.value = responseState
                        }.launchIn(viewModelScope)
                    }
                }

                is SearchArtwork -> {
                    if (!searchQuery.isNullOrEmpty()) {
                        repository.searchArtwork(searchQuery!!, currentPage).onEach { responseState ->
                            _responseState.value = responseState
                        }.launchIn(viewModelScope)
                    }
                }

                is None -> {}
            }
        }
    }

    fun pageForward(isSearchView: Boolean) {
        currentPage++
        if (isSearchView) {
            performEvent(SearchArtwork)
        } else {
            performEvent(GetArtworksInDepartment)
        }
    }

    fun pageBack(isSearchView: Boolean) {
        currentPage--
        if (isSearchView) {
            performEvent(SearchArtwork)
        } else {
            performEvent(GetArtworksInDepartment)
        }
    }
}


sealed class ArtworkListEvent {
    object GetArtworksInDepartment: ArtworkListEvent()

    object SearchArtwork: ArtworkListEvent()

    object None: ArtworkListEvent()
}