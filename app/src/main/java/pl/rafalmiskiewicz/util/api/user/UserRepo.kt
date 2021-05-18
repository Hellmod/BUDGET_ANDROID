package pl.rafalmiskiewicz.util.api.user

import arrow.core.Either
import pl.rafalmiskiewicz.data.login.LoginResponse
import pl.rafalmiskiewicz.util.errorhandling.Failure

interface UserRepo {

    suspend fun loginUser(
        email: String,
        password: String
    ): Either<Failure, LoginResponse>
}