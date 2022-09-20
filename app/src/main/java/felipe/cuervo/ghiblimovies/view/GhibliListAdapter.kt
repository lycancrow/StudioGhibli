package felipe.cuervo.ghiblimovies.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import felipe.cuervo.ghiblimovies.R
import felipe.cuervo.ghiblimovies.databinding.ViewFavoriteElementBinding
import felipe.cuervo.ghiblimovies.model.GhibliMovies
import felipe.cuervo.ghiblimovies.util.getProgressDrawable
import felipe.cuervo.ghiblimovies.util.loadImage
import kotlinx.android.synthetic.main.view_favorite_element.view.*

class GhibliListAdapter (var movies: ArrayList<GhibliMovies>,
                         private var movieOnClickListener: OnMovieClickListener):RecyclerView.Adapter<
        GhibliListAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_favorite_element, parent,false)
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position],movieOnClickListener)
    }

    override fun getItemCount() = movies.size

    fun updateMovies(newMovies: List<GhibliMovies>){
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    class MovieViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val imageView = view.ghibliImage
        private val ghibliMovie = view.ghibliMovie
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind (movies: GhibliMovies, onMovieClickListener: OnMovieClickListener){
            ghibliMovie.text = movies.title
            imageView.loadImage(movies.image, progressDrawable)
            itemView.setOnClickListener{
                onMovieClickListener.onMoviesClick(movies)
            }
        }
    }

}

interface OnMovieClickListener{
    fun onMoviesClick(movieClick: GhibliMovies)
}