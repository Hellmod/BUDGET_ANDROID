package pl.rafalmiskiewicz.util.api

class MainRepository constructor(private val retrofitService: AdozlApi) {

    fun getAllMovies() = retrofitService.getAllMovies()
}