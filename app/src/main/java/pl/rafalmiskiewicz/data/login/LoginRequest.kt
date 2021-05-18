package pl.rafalmiskiewicz.data.login

import androidx.annotation.Keep

@Keep
data class LoginRequest(val login: String, val password: String)