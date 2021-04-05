package com.example.aicvirtualtour.ui

import android.media.Image
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aicvirtualtour.data.AICRepository
import com.example.aicvirtualtour.data.ResponseState
import com.example.aicvirtualtour.models.Artwork
import com.example.aicvirtualtour.ui.ArtworkEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
    private val repository: AICRepository
) : ViewModel() {
    private val _responseState: MutableLiveData<ResponseState<Artwork>> = MutableLiveData()
    val responseState: LiveData<ResponseState<Artwork>>
        get() = _responseState
    var artworkId: Int? = null
    var imageId: Int? = null

    fun performEvent(artworkEvent: ArtworkEvent) {
        viewModelScope.launch {
            when(artworkEvent) {
                is GetArtwork -> {
                    if (artworkId != null) {
                        repository.getArtwork(artworkId!!).onEach { responseState ->
                            _responseState.value = responseState
                        }.launchIn(viewModelScope)
                    }
                }

                is None -> {}
            }
        }
    }

    fun getArtworkImageForView(imageId: String, imageView: ImageView) {
        repository.loadImageFromPicasso(imageId)?.into(imageView)
    }
}


sealed class ArtworkEvent {
    object GetArtwork: ArtworkEvent()

    object None: ArtworkEvent()
}