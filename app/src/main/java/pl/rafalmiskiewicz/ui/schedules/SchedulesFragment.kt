package pl.rafalmiskiewicz.ui.schedules

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import pl.rafalmiskiewicz.util.errorhandling.ErrorHandler
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.corelogic.supplify.util.extensions.observeEvent
import pl.corelogic.supplify.util.extensions.observeFailure
import pl.rafalmiskiewicz.databinding.FragmentSchedulesBinding
import pl.rafalmiskiewicz.util.errorhandling.Failure

class SchedulesFragment() : Fragment() {

    private val schedulesViewModel by viewModel<SchedulesViewModel>()
    private val mAdapter by inject<SchedulesAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSchedulesBinding.inflate(inflater, container, false).apply {
            viewModel = schedulesViewModel
            lifecycleOwner = viewLifecycleOwner
            favouritesList.apply {
                adapter = mAdapter
            }
        }


        initObservers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        schedulesViewModel.getAllSchedules()
    }

    private fun initObservers() {
        observeEvent(schedulesViewModel.event, ::handleEvent)
        observeFailure(schedulesViewModel.errorEvent, ::handleError)
    }

    private fun handleEvent(event: SchedulesEvent?) {
        when (event) {
            is SchedulesEvent.OpenMap -> openMap(event.addressGoogle)
        }
    }

    private fun handleError(failure: Failure?) {
        (activity as? ErrorHandler)?.onFailure(failure)
    }

    private fun openMap(address_google:String) {
        context?.let {
            val uri: Uri =
                Uri.parse(address_google) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            ContextCompat.startActivity(it, intent, null)
        }

    }
}