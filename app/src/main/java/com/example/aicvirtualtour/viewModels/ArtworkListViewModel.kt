package com.example.aicvirtualtour.viewModels

import androidx.lifecycle.ViewModel
import com.example.aicvirtualtour.data.AICRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtworkListViewModel @Inject constructor(
    private val repository: AICRepository
) : ViewModel() {
    var departmentId: String? = null
    var page: Int = 1
    val artworkIds by lazy {
        departmentId?.let {  repository.getArtworksInDepartment(it, page) }
    }

    // Called when pagination buttons are touched. Update page number and reload data
    fun pageForward() {
        page++
        departmentId?.let { repository.getArtworksInDepartment(it, page) }
    }
    fun pageBack() {
        page++
        departmentId?.let { repository.getArtworksInDepartment(it, page) }
    }

}