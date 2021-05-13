package pl.rafalmiskiewicz.util.errorhandling

interface ErrorHandler {

    fun onFailure(failure: Failure?)
    fun onConnectivityUnavailable()
    fun internalServerError()
    fun notFound()
    fun serviceUnavailable()
    fun unauthorized()
    fun onTimeout()
    fun defaultSpecificError()
    fun notImplemented()
}