package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.model.Albums
import retrofit2.Call
import retrofit2.http.GET

interface AdozlApi {

    @GET("albums")
    fun getAllMovies() : Call<List<Albums>>
}