package pl.rafalmiskiewicz.ui.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import pl.corelogic.supplify.util.extensions.longToast
import pl.rafalmiskiewicz.R
import pl.rafalmiskiewicz.util.errorhandling.ErrorHandler
import pl.rafalmiskiewicz.util.errorhandling.Failure

abstract class BaseActivity : AppCompatActivity(), ErrorHandler {

    override fun onFailure(failure: Failure?) {
        when (failure) {
            Failure.ServerError -> internalServerError()
            Failure.Unauthorized -> unauthorized()
            Failure.NetworkConnection -> onConnectivityUnavailable()
            Failure.ServiceUnavailable -> serviceUnavailable()
            Failure.NotFound -> notFound()
            Failure.TimeOut -> onTimeout()
            Failure.Unknown -> defaultSpecificError()
            Failure.NotImplemented -> notImplemented()
            else -> Unit
        }
    }

    override fun onConnectivityUnavailable() {
        longToast(R.string.error_check_your_network)
    }

    override fun internalServerError() {
        longToast(R.string.error_internal_server)
    }

    override fun notFound() {
        longToast(R.string.error_not_found)
    }

    override fun serviceUnavailable() {
        longToast(R.string.error_service_unavailable)
    }

    override fun unauthorized() {
        longToast(R.string.error_unauthorized)
        logOut()
    }

    override fun onTimeout() {
        longToast(R.string.error_request_taking_too_long)
    }

    override fun defaultSpecificError() {
        longToast(R.string.error_unknown)
    }

    private fun logOut() {
        TODO("Not implemented")
    }

    override fun notImplemented() {
        AlertDialog.Builder(this)
            .setTitle(R.string.info)
            .setMessage(R.string.error_not_implemented)
            .setIcon(R.drawable.ic_not_implemented)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}