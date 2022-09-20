package felipe.cuervo.ghiblimovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import felipe.cuervo.ghiblimovies.R
import felipe.cuervo.ghiblimovies.databinding.ActivityMainBinding
import felipe.cuervo.ghiblimovies.databinding.ActivityMovieDetailBinding
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.w3c.dom.Text

class MovieDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var title = intent.getStringExtra("title")
        var imageUrl = intent.getStringExtra("image")
        var originalTitle = intent.getStringExtra("originalTitle")
        var decriptionMovie = intent.getStringExtra("description")
        var director = intent.getStringExtra("director")
        var producer = intent.getStringExtra("producer")
        var movieReleaseDate = intent.getStringExtra("date")
        var qualification = intent.getStringExtra("qualification")
        binding.movieTitle.text = title
        Glide.with(this).load(imageUrl).into(binding.movieImage)
        binding.originalName.text = originalTitle
        binding.description.text = decriptionMovie
        binding.director.text = director
        binding.producer.text = producer
        binding.releaseDate.text = movieReleaseDate
        binding.qualification.text = qualification

    }
}