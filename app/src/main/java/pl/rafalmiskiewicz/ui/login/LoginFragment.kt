package pl.rafalmiskiewicz.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pl.rafalmiskiewicz.util.errorhandling.ErrorHandler
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.corelogic.supplify.util.extensions.observeEvent
import pl.corelogic.supplify.util.extensions.observeFailure
import pl.rafalmiskiewicz.R
import pl.rafalmiskiewicz.databinding.FragmentLoginBinding
import pl.rafalmiskiewicz.util.errorhandling.Failure

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
            is LoginEvent.MoveToHours -> findNavController().navigate(R.id.action_loginFragment_to_hoursFragment)
        }
    }

    private fun login(login: String, password: String) {
      //  loginViewModel.getAllMovies()
        loginViewModel.login(login,password)

    }

    private fun handleError(failure: Failure?) {
        (activity as? ErrorHandler)?.onFailure(failure)
    }
}