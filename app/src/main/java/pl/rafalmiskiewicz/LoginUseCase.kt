package pl.rafalmiskiewicz

import arrow.core.Either
import pl.rafalmiskiewicz.domain.BaseUseCase
import pl.rafalmiskiewicz.data.api.enums.AccountType
import pl.rafalmiskiewicz.data.source.local.AppPreferences
import pl.rafalmiskiewicz.data.source.local.CredentialStore
import pl.rafalmiskiewicz.util.api.user.UserRepo
import pl.rafalmiskiewicz.util.errorhandling.Failure

class LoginUseCase(
    private val userRepo: UserRepo,
    private val credentialStore: CredentialStore,
    private val appPreferences: AppPreferences
) : BaseUseCase<AccountType, LoginUseCase.LoginParams>() {

    class LoginParams(val email: String, val password: String)

    override suspend fun run(params: LoginParams): Either<Failure, AccountType> {

        return userRepo.loginUser(params.email, params.password)
            .map {
                appPreferences.automaticLogOutTimestamp = 0
                credentialStore.store(params.email, params.password, it.token)
                credentialStore.isLogged = true
                it.status
            }
    }
}