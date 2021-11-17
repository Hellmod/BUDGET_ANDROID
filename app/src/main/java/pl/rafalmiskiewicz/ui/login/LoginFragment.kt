package pl.rafalmiskiewicz.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.rafalmiskiewicz.util.extensions.observeEvent
import pl.rafalmiskiewicz.R
import pl.rafalmiskiewicz.databinding.FragmentLoginBinding
import pl.rafalmiskiewicz.ui.MainActivity

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
    }

    private fun handleEvent(event: LoginEvent?) {
        when (event) {
            is LoginEvent.Login -> loginViewModel.login(
                event.login,
                event.password,
                (activity as? MainActivity)?.getToken()
            )
            is LoginEvent.LogOut -> loginViewModel.logOut()
            is LoginEvent.MoveToHours -> findNavController().navigate(R.id.action_loginFragment_to_hoursFragment)
            is LoginEvent.MoveToTransaction -> findNavController().navigate(R.id.action_loginFragment_to_transactionFragment)
        }
    }
}