package com.example.aicvirtualtour.viewModels

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.aicvirtualtour.data.AICRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
    private val repository: AICRepository
) : ViewModel() {
    var id: Int? = null
    val artwork by lazy { id?.let { repository.getArtwork(it) } }

    /**
     * The AIC uses a different service to load the actual images of the artwork. The data returned
     * from the getArtwork(artworkId) call supplies an image_id value. This can be given to to AIC's
     * IIIF API to supply an image.
     * This function takes the image's id and the image view it will appear in as parameters, calls
     * the image API to get the image, and uses the Picasso framework to insert the result image
     * into the image view.
     */
    fun getArtworkImageForView(imageId: String, imageView: ImageView) {
        repository.loadImageFromPicasso(imageId)?.into(imageView)
    }
}