package com.example.aicvirtualtour.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.aicvirtualtour.R
import com.example.aicvirtualtour.data.ResponseState
import com.example.aicvirtualtour.models.Artwork
import com.example.aicvirtualtour.viewModels.ArtworkEvent.*
import com.example.aicvirtualtour.viewModels.ArtworkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val artworkId = args.artworkId
        viewModel.artworkId = artworkId

        setupObservers()
        viewModel.performEvent(GetArtwork)
    }

    private fun setupObservers() {
        viewModel.responseState.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is ResponseState.Success<Artwork> -> {
                    displayProgressBar(false)
                    val artwork = response.data
                    artwork.let {
                        view?.findViewById<TextView>(R.id.artworkTitleText)?.let { it.text = artwork.title }
                        view?.findViewById<TextView>(R.id.artworkDateDisplay)?.let { it.text = artwork.dateCreated }
                        view?.findViewById<TextView>(R.id.artworkArtistDisplay)?.let { it.text = artwork.artist }
                        view?.findViewById<TextView>(R.id.artworkMediumDisplay)?.let { it.text = artwork.medium }
                        view?.findViewById<TextView>(R.id.artworkDimensions)?.let { it.text = artwork.dimensions }

                        val artworkImage = view?.findViewById(R.id.artworkImage) as ImageView
                        viewModel.getArtworkImageForView(artwork.imageId, artworkImage)
                    }
                }
                is ResponseState.Error ->
                    // TODO: Change this to say artwork, put in strings.xml
                    Toast.makeText(requireContext(), "Error receiving departments. Please try again later.", Toast.LENGTH_SHORT).show()
                is ResponseState.Loading ->
                    displayProgressBar(true)
            }
        })
    }

    private fun displayProgressBar(shouldShow: Boolean) {
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)
        progressBar?.visibility = if(shouldShow) View.VISIBLE else View.GONE
    }
}