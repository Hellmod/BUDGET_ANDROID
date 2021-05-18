package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.data.login.LoginRequest
import pl.rafalmiskiewicz.data.login.LoginResponse
import retrofit2.http.*

interface AdozlApi {

    @POST("api/login")
    suspend fun login(@Body login: LoginRequest): LoginResponse
}