package pl.rafalmiskiewicz.util.api.user

import arrow.core.Either
import pl.rafalmiskiewicz.data.login.LoginRequest
import pl.rafalmiskiewicz.data.login.LoginResponse
import pl.rafalmiskiewicz.util.api.AdozlApi
import pl.rafalmiskiewicz.util.errorhandling.Failure
import pl.rafalmiskiewicz.util.errorhandling.handleRequest
import pl.rafalmiskiewicz.util.errorhandling.mapLeftIfErrorCode

class UserRepoImpl(private val api: AdozlApi) : UserRepo {

    override suspend fun loginUser(
        email: String,
        password: String
    ): Either<Failure, LoginResponse> {
        return handleRequest {
            api.login(LoginRequest(login = email, password = password))
        }.mapLeftIfErrorCode(
            //ToDo error
        )
    }
}

