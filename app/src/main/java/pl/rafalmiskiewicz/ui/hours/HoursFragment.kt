package pl.rafalmiskiewicz.ui.hours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.rafalmiskiewicz.util.errorhandling.ErrorHandler
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.corelogic.supplify.util.extensions.observeEvent
import pl.corelogic.supplify.util.extensions.observeFailure
import pl.rafalmiskiewicz.databinding.FragmentHoursBinding
import pl.rafalmiskiewicz.databinding.FragmentLoginBinding
import pl.rafalmiskiewicz.util.errorhandling.Failure

class HoursFragment() : Fragment() {

    private val hoursViewModel by viewModel<HoursViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHoursBinding.inflate(inflater, container, false).apply {
            viewModel = hoursViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        initObservers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        hoursViewModel.getAllHours()
    }

    private fun initObservers() {
        observeEvent(hoursViewModel.event, ::handleEvent)
        observeFailure(hoursViewModel.errorEvent, ::handleError)
    }

    private fun handleEvent(event: HoursEvent?) {

    }

    private fun handleError(failure: Failure?) {
        (activity as? ErrorHandler)?.onFailure(failure)
    }
}