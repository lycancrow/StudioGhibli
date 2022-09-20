package felipe.cuervo.ghiblimovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import felipe.cuervo.ghiblimovies.databinding.ActivityMainBinding
import felipe.cuervo.ghiblimovies.model.GhibliMovies
import felipe.cuervo.ghiblimovies.viewmodel.RecyclerViewModel

//https://ghibliapi.herokuapp.com/films/
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RecyclerViewModel
    private val ghibliAdapter = GhibliListAdapter(arrayListOf(), object : OnMovieClickListener {
        override fun onMoviesClick(movieClick: GhibliMovies) {
            viewModel.onMovieClicked(movieClick)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        screenSplash.setKeepOnScreenCondition { false }
        viewModel = ViewModelProviders.of(this).get(RecyclerViewModel::class.java)

        viewModel.getMovies()

        viewModel.navigateToDetails.observe(this) {
            it?.let {
                val intent = Intent(this, MovieDetail::class.java).apply {
                    putExtra("title", it.title)
                    putExtra("originalTitle", it.originalName)
                    putExtra("description", it.description)
                    putExtra("director", it.director)
                    putExtra("producer", it.producer)
                    putExtra("date", it.release_date)
                    putExtra("image", it.movieBanner)
                    putExtra("qualification", it.rt_score)
                }
                startActivity(intent)
            }
        }
        binding.movieList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ghibliAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {

        viewModel.movies.observe(this, Observer { movies ->
            movies?.let {
                ghibliAdapter.updateMovies(it)
            }
        })

    }


}