package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.data.api.Login

class MainRepository constructor(
    private val retrofitService: AdozlApi
) {

    fun getAllHours() =
        retrofitService.getAllHours()

    fun login(email: String, password: String) =
        retrofitService.login("application/json", Login(username = email, password = password))

    fun loginFake(email: String, password: String) =
        retrofitService.loginFake("application/json")
}