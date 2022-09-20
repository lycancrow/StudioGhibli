package felipe.cuervo.ghiblimovies.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import felipe.cuervo.ghiblimovies.dependencyInjection.DaggerApiComponent
import felipe.cuervo.ghiblimovies.model.GhibliMovies
import felipe.cuervo.ghiblimovies.model.GhibliService
import felipe.cuervo.ghiblimovies.view.MainActivity
import felipe.cuervo.ghiblimovies.view.MovieDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RecyclerViewModel: ViewModel(){
    @Inject
    lateinit var ghibliService: GhibliService
    init {
        DaggerApiComponent.create().inject(this)
    }
    private val disposable = CompositeDisposable()
    val movies = MutableLiveData<List<GhibliMovies>>()
    private val _navigateToDetails = MutableLiveData<GhibliMovies?>()
    val navigateToDetails: LiveData<GhibliMovies?> get() = _navigateToDetails


    fun getMovies(){
        fetchMovies()
    }

    private fun fetchMovies(){
        disposable.add(
            ghibliService.getMovies().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<GhibliMovies>>(){
                    override fun onSuccess(value: List<GhibliMovies>?) {
                        movies.value = value
                    }
                    override fun onError(e: Throwable?) {
                        Log.e("error",e.toString())
                    }
                })
        )
    }


    fun onMovieClicked(movie:GhibliMovies){
        _navigateToDetails.value = movie
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}