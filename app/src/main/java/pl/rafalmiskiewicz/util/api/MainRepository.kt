package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.data.api.Login

class MainRepository constructor(
    private val retrofitService: AdozlApi
) {

    fun getLeftAmount() =
        retrofitService.getLeftAmount()

    fun getAllPlan() =
        retrofitService.getAllPlan()

    fun getAllTransaction() =
        retrofitService.getAllTransaction()

    fun login(email: String, password: String) =
        retrofitService.login("application/json", Login(username = email, password = password))

    fun loginFake(email: String, password: String) =
        retrofitService.loginFake("application/json")

    fun refreshToken(token: String) = retrofitService.refreshToken(token)
}