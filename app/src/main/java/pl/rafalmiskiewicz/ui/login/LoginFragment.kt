package pl.rafalmiskiewicz.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.rafalmiskiewicz.util.errorhandling.ErrorHandler
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.corelogic.supplify.util.extensions.observeEvent
import pl.corelogic.supplify.util.extensions.observeFailure
import pl.rafalmiskiewicz.databinding.FragmentLoginBinding
import pl.rafalmiskiewicz.model.Login
import pl.rafalmiskiewicz.model.User
import pl.rafalmiskiewicz.model.UserClient
import pl.rafalmiskiewicz.util.errorhandling.Failure
import retrofit2.HttpException

class LoginFragment() : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false).apply {
            viewModel = loginViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        initObservers()

        return binding.root
    }

    private fun initObservers() {
        observeEvent(loginViewModel.event, ::handleEvent)
        observeFailure(loginViewModel.errorEvent, ::handleError)
    }

    private fun handleEvent(event: LoginEvent?) {
        when (event) {
            is LoginEvent.Login -> login(event.login, event.password)
        }
    }

    private fun login(login: String, password: String) {

       // val response = api.login(Login(login, password))
        loginViewModel.getAllMovies()

    }

    private fun handleError(failure: Failure?) {
        (activity as? ErrorHandler)?.onFailure(failure)
    }
}