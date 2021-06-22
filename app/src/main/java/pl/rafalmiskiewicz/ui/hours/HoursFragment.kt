package pl.rafalmiskiewicz.ui.hours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.rafalmiskiewicz.util.extensions.observeEvent
import pl.rafalmiskiewicz.databinding.FragmentHoursBinding

class HoursFragment() : Fragment() {

    private val hoursViewModel by viewModel<HoursViewModel>()
    private val mAdapter by inject<HoursAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHoursBinding.inflate(inflater, container, false).apply {
            viewModel = hoursViewModel
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
        hoursViewModel.getAllHours()
    }

    private fun initObservers() {
        observeEvent(hoursViewModel.event, ::handleEvent)
    }

    private fun handleEvent(event: HoursEvent?) {

    }
}