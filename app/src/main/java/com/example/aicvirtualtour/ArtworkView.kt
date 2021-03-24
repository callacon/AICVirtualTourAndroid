package com.example.aicvirtualtour

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ArtworkView : Fragment(), CoroutineScope by MainScope() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artwork_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ArtworkViewArgs by navArgs()
        val artworkId = args.ArtworkId

        launch(Dispatchers.Main) {
            try {
                val response = AICDataManager.apiClient.getArtwork(artworkId)
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!.getValue("data").let {artwork ->
                        view.findViewById<TextView>(R.id.artworkTitleText).text = artwork.title
                        view.findViewById<TextView>(R.id.artworkDateDisplay).text = artwork.dateCreated
                        view.findViewById<TextView>(R.id.artworkArtistDisplay).text = artwork.artist
                        view.findViewById<TextView>(R.id.artworkMediumDisplay).text = artwork.medium
                        view.findViewById<TextView>(R.id.artworkDimensions).text = artwork.dimensions
                        getArtworkImageForView(artwork.imageId, view.findViewById(R.id.artworkImage) as ImageView)
                    }
                }
            } catch (e: Exception) {
                Log.e("error:", e.message.toString())
            }
        }
    }

    private suspend fun getArtworkImageForView(imageId: String, imageView: ImageView) {
        AICDataManager.loadImageFromPicasso(imageId)?.into(imageView)
    }
}