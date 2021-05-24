package pl.rafalmiskiewicz.ui.login

import androidx.lifecycle.MutableLiveData
import pl.rafalmiskiewicz.common.Validator.isValidEmail
import pl.rafalmiskiewicz.model.Albums
import pl.rafalmiskiewicz.ui.base.BaseViewModel
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: MainRepository) : BaseViewModel<LoginEvent>() {

    val emailErrorText = MutableLiveData<String>()
    val passwordErrorText = MutableLiveData<String>()
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    val movieList = MutableLiveData<List<Albums>>()
    val movieListString = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()

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
            passwordErrorText.value =
                "Wprowadź hasło"// resourceProvider.getString(R.string.provide_password)
            isValid = false
        }
        if (isValid && email != null && pass != null) afterValidation(email, pass)
    }

    fun getAllMovies() {

        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<Albums>> {
            override fun onResponse(call: Call<List<Albums>>, response: Response<List<Albums>>) {
                movieList.postValue(response.body())
                movieListString.value = movieList.value?.get(0).toString()
            }

            override fun onFailure(call: Call<List<Albums>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}