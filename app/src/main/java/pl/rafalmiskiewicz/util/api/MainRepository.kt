package pl.rafalmiskiewicz.util.api

import pl.rafalmiskiewicz.model.Login

class MainRepository constructor(private val retrofitService: AdozlApi) {

    fun getAllMovies() = retrofitService.getAllMovies()

    fun login(email: String, password: String) =
        retrofitService.login("application/json",Login(login = email, password = password))
}