package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.data.api.Hours
import pl.rafalmiskiewicz.data.api.Login
import pl.rafalmiskiewicz.data.api.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AdozlApi {

    @GET("hour")
    fun getAllHours(@Header("Authorization") token: String): Call<List<Hours>>

    @POST("authenticate")
    fun login(@Header("Content-Type") value: String, @Body login: Login): Call<User>
}