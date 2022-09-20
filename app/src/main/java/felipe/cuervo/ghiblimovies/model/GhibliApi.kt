package felipe.cuervo.ghiblimovies.model

import io.reactivex.Single
import retrofit2.http.GET

interface GhibliApi {
    @GET("/films/")
    fun getMovies(): Single<List<GhibliMovies>>
}