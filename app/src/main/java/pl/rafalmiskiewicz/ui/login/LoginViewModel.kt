package pl.rafalmiskiewicz.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pl.rafalmiskiewicz.common.Validator.isValidEmail
import pl.rafalmiskiewicz.data.api.Left
import pl.rafalmiskiewicz.data.api.User
import pl.rafalmiskiewicz.data.source.local.CredentialStore
import pl.rafalmiskiewicz.ui.base.BaseViewModel
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    private val repository: MainRepository,
    private val credentialStore: CredentialStore,
) : BaseViewModel<LoginEvent>() {

    val emailErrorText = MutableLiveData<String>()
    val passwordErrorText = MutableLiveData<String>()
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val user = MutableLiveData<User>()
    val isLogin = MutableLiveData<Boolean>(false)
    val amountLeft = MutableLiveData<Left>()

    init {
        isLogin.value = credentialStore.isLogged
    }

    fun onLoginClicked() {
        validateAndHandleForm(email.value, password.value) { email, pass ->
            sendEvent(LoginEvent.Login(email, pass))
        }
    }

    fun onLogOutClicked() {
        sendEvent(LoginEvent.LogOut)
    }

    fun onPlanClicked() {
        sendEvent(LoginEvent.MoveToPlan)
    }

    fun onTransactionClicked() {
        sendEvent(LoginEvent.MoveToTransaction)
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
            passwordErrorText.value =
                "Wprowadź hasło"// resourceProvider.getString(R.string.provide_password)
            isValid = false
        }
        if (isValid && email != null && pass != null) afterValidation(email, pass)
    }

    fun login(email: String, password: String, token: String?) {

        showProgress()
        //val response = repository.login(email, password)
        val response = repository.loginFake(email, password)
        response.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                hideProgress()

                user.postValue(response.body())
                user.value.let { params ->
                    if (params != null) {
                        credentialStore.store(email, password, params.jwt)
                        credentialStore.isLogged = true
                        isLogin.value = true
                    }
                }
                getLeftAmount()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                hideProgress()
                t.message?.let { Log.d("RMRM", "ERROR:" + it) }
                isLogin.value = false
            }
        })

        token?.let {
            repository.refreshToken(it)
        }
    }

    private fun getLeftAmount() {
        val response = repository.getLeftAmount()
        response.enqueue(object : Callback<Left> {
            override fun onResponse(call: Call<Left>, response: Response<Left>) {
                var left: String? = response.body()?.amount
                left?.let{
                    amountLeft.value = Left(amount = it + " zł")
                }
            }

            override fun onFailure(call: Call<Left>, t: Throwable) {
                amountLeft.value = Left(amount = "")
            }
        })
    }

    fun logOut() {
        credentialStore.isLogged = false
        isLogin.value = false
        credentialStore.store("", "", "")
    }

}