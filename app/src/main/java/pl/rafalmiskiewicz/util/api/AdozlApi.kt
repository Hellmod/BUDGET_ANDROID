package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.model.Albums
import pl.rafalmiskiewicz.model.Login
import pl.rafalmiskiewicz.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AdozlApi {

    @GET("albums")
    fun getAllMovies(): Call<List<Albums>>

    @POST("login")
    fun login(@Header("Content-Type") value: String, @Body login: Login): Call<User>
}