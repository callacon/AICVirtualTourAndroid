package com.example.aicvirtualtour.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.aicvirtualtour.viewModels.ArtworkViewModel
import com.example.aicvirtualtour.R
import com.example.aicvirtualtour.data.Result
import com.example.aicvirtualtour.models.Artwork

class ArtworkFragment : Fragment() {

    private val viewModel: ArtworkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artwork, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the artwork's ID for API call
        val args: ArtworkFragmentArgs by navArgs()
        val artworkId = args.ArtworkId
        viewModel.id = artworkId

        setupArtworkDisplay()

//        launch(Dispatchers.Main) {
//            try {
//                val response = AICDataManager.apiClient.getArtwork(artworkId)
//                if (response.isSuccessful && response.body() != null) {
//                    // Populate art data in view from API response
//                    response.body()!!.getValue("data").let {artwork ->
//                        view.findViewById<TextView>(R.id.artworkTitleText).text = artwork.title
//                        view.findViewById<TextView>(R.id.artworkDateDisplay).text = artwork.dateCreated
//                        view.findViewById<TextView>(R.id.artworkArtistDisplay).text = artwork.artist
//                        view.findViewById<TextView>(R.id.artworkMediumDisplay).text = artwork.medium
//                        view.findViewById<TextView>(R.id.artworkDimensions).text = artwork.dimensions
//                        getArtworkImageForView(artwork.imageId, view.findViewById(R.id.artworkImage) as ImageView)
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("error:", e.message.toString())
//            }
//        }
    }

    private fun setupArtworkDisplay() {
        lateinit var artwork: Artwork
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)

        viewModel.artwork?.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Result.Status.SUCCESS -> {
                    progressBar?.visibility = View.GONE
                    artwork = it.data!!
                }
                Result.Status.LOADING ->
                    progressBar?.visibility = View.VISIBLE
            }
        })

        viewModel.artwork?.let {
            view?.findViewById<TextView>(R.id.artworkTitleText)?.let { it.text = artwork.title }
            view?.findViewById<TextView>(R.id.artworkDateDisplay)?.let { it.text = artwork.dateCreated }
            view?.findViewById<TextView>(R.id.artworkArtistDisplay)?.let { it.text = artwork.artist }
            view?.findViewById<TextView>(R.id.artworkMediumDisplay)?.let { it.text = artwork.medium }
            view?.findViewById<TextView>(R.id.artworkDimensions)?.let { it.text = artwork.dimensions }

            val artworkImage = view?.findViewById(R.id.artworkImage) as ImageView
            viewModel.getArtworkImageForView(artwork.imageId, artworkImage)
        }


    }
}