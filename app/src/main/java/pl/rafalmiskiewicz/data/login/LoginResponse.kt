package pl.rafalmiskiewicz.data.login

import androidx.annotation.Keep
import pl.rafalmiskiewicz.data.api.enums.AccountType

@Keep
data class LoginResponse(val token: String, val status: AccountType)