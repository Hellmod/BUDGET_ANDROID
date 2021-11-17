package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.data.api.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AdozlApi {

    @GET("left")
    fun getLeftAmount(): Call<Left>

    @GET("left")
    fun getAllPlan(): Call<List<Plan>>

    @GET("transaction")
    fun getAllTransaction(): Call<List<Transaction>>

    @POST("authenticate")
    fun login(@Header("Content-Type") value: String, @Body login: Login): Call<User>

    @GET("authenticate")
    fun loginFake(@Header("Content-Type") value: String): Call<User>

    @POST("refreshToken")
    fun refreshToken(@Body refreshToken: String): Call<String>
}