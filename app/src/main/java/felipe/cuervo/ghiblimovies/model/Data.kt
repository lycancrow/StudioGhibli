package felipe.cuervo.ghiblimovies.model

import com.google.gson.annotations.SerializedName

data class GhibliMovies(
    @SerializedName("title")
    val title:String?,
    @SerializedName("original_title")
    val originalName:String?,
    @SerializedName("original_name_romanised")
    val originalNameRomanised:String?,
    @SerializedName("image")
    val image:String?,
    @SerializedName("movie_banner")
    val movieBanner:String?,
    @SerializedName("description")
    val description:String?,
    @SerializedName("director")
    val director:String?,
    @SerializedName("producer")
    val producer:String?,
    @SerializedName("release_date")
    val release_date:String?,
    @SerializedName("running_time")
    val running_time:String?,
    @SerializedName("rt_score")
    val rt_score:String?
)