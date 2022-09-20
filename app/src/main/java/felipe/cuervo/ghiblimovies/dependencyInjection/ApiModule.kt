package felipe.cuervo.ghiblimovies.dependencyInjection

import dagger.Module
import dagger.Provides
import felipe.cuervo.ghiblimovies.model.GhibliApi
import felipe.cuervo.ghiblimovies.model.GhibliService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://ghibliapi.herokuapp.com"

    @Provides
    fun provideMoviesApi(): GhibliApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GhibliApi::class.java)
    }
    @Provides
    fun provideGhibliService(): GhibliService {
        return GhibliService()
    }
}