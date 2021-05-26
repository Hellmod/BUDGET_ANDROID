package pl.rafalmiskiewicz.model

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserClient {

    @POST("api/login")
    fun login(@Body login: Login): User

    @GET("api/schedule")
    fun getSchedule(@Header("Authorization") authToken: String): ResponseBody

}