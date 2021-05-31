package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.data.api.Login
import pl.rafalmiskiewicz.data.source.local.AppPreferences

class MainRepository constructor(
    private val retrofitService: AdozlApi,
    private val appPreferences: AppPreferences
) {

    fun getAllHours() =
        retrofitService.getAllHours("Bearer "+appPreferences.userToken)

    fun login(email: String, password: String) =
        retrofitService.login("application/json", Login(username = email, password = password))
}