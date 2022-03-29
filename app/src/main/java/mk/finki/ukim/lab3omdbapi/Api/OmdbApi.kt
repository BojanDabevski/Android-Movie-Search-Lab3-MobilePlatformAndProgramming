package mk.finki.ukim.lab3omdbapi.Api


import mk.finki.ukim.lab3omdbapi.Models.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    fun getMoviesBySearch(
        @Query("apikey") apikey: String,
        @Query("s") s: String
    ): Call<SearchResult>
}