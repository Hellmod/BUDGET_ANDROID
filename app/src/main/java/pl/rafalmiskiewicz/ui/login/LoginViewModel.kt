package pl.rafalmiskiewicz.ui.login

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import pl.rafalmiskiewicz.R
import pl.rafalmiskiewicz.common.Validator.isValidEmail
import pl.rafalmiskiewicz.ui.base.BaseViewModel

class LoginViewModel : BaseViewModel<LoginEvent>() {

    val emailErrorText = MutableLiveData<String>()
    val passwordErrorText = MutableLiveData<String>()
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    init {

    }

    fun onLoginClicked() {
        validateAndHandleForm(email.value, password.value) { email, pass ->
            sendEvent(LoginEvent.Login(email, pass))
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