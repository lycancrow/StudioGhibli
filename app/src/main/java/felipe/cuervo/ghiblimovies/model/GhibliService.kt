package felipe.cuervo.ghiblimovies.model


import felipe.cuervo.ghiblimovies.dependencyInjection.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject


class GhibliService {
    @Inject
    lateinit var api: GhibliApi
    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getMovies(): Single<List<GhibliMovies>> {
        return api.getMovies()
    }
}