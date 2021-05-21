package pl.rafalmiskiewicz.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import pl.rafalmiskiewicz.LoginUseCase
import pl.rafalmiskiewicz.common.Validator.isValidEmail
import pl.rafalmiskiewicz.data.source.local.CredentialStore
import pl.rafalmiskiewicz.ui.base.BaseViewModel
import pl.rafalmiskiewicz.util.config.ResourceProvider

class LoginViewModel(
    private val credentialStore: CredentialStore,
    private val loginUseCase: LoginUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<LoginEvent>() {

    val emailErrorText = MutableLiveData<String>()
    val passwordErrorText = MutableLiveData<String>()
    val email = MutableLiveData(credentialStore.restore().login)
    val password = MutableLiveData("")

    init {

    }

    fun onLoginClicked() {
        validateAndHandleForm(email.value, password.value) { email, pass ->
            sendEvent(LoginEvent.Login(email, pass))
            sendLoginRequest(email, pass)
        }
    }

    private fun sendLoginRequest(email: String, password: String) {
        showProgress()
        loginUseCase.invoke(viewModelScope, LoginUseCase.LoginParams(email, password)) {
            hideProgress()
            it.fold({ failure ->
                sendError(failure)
            }, {

            })
        }
    }

    private fun validateAndHandleForm(
        email: String?,
        pass: String?,
        afterValidation: (email: String, pass: String) -> Unit
    ) {
        var isValid = true
        if (email.isNullOrEmpty()) {
            emailErrorText.value =
                "Wprowadź adres email"//resourceProvider.getString(R.string.provide_email)
            isValid = false
        } else if (!isValidEmail(email)) {
            emailErrorText.value =
                "podaj poprawny aders emiail"//resourceProvider.getString(R.string.provide_correct_email)
            isValid = false
        }
        if (pass.isNullOrEmpty()) {
            passwordErrorText.value ="Wprowadź hasło"// resourceProvider.getString(R.string.provide_password)
            isValid = false
        }
        if (isValid && email != null && pass != null) afterValidation(email, pass)
    }

}