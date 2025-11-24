package com.example.cuidartrite.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_EXERCISE
import com.example.cuidartrite.constants.ConstantsExtra.Companion.EXTRA_USER
import com.example.cuidartrite.databinding.ActivityPracticingTechiniqueBinding
import com.example.cuidartrite.network.models.TecnicaDetalheResponse
import com.example.cuidartrite.network.models.User

class PracticingTechiniqueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticingTechiniqueBinding
    private lateinit var user: User
    private lateinit var exercise: TecnicaDetalheResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticingTechiniqueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra(EXTRA_USER)!!
        exercise = intent.getParcelableExtra(EXTRA_EXERCISE)!!

        binding.tvTitle.text = exercise.titulo
        binding.tvBeneficios.text = exercise.beneficios
        binding.tvComoFazer.text = exercise.comoFazer
        binding.tvDica.text = exercise.dica

        //setupYoutubeVideo(exercise.videoUrl)
    }

//    private fun setupYoutubeVideo(url: String) {
//        val playerView = binding.youtubePlayerView
//
//        lifecycle.addObserver(playerView)
//
//        val videoId = extractVideoId(url)
//
//        playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                youTubePlayer.loadVideo(videoId, 0f)
//            }
//        })
//    }

    private fun extractVideoId(url: String): String {
        return when {
            "watch?v=" in url -> url.substringAfter("watch?v=").substringBefore("&")
            "youtu.be/" in url -> url.substringAfter("youtu.be/").substringBefore("?")
            else -> url
        }
    }
}
