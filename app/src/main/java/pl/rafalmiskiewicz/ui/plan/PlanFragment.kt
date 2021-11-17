package pl.rafalmiskiewicz.ui.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.rafalmiskiewicz.databinding.FragmentPlanBinding
import pl.rafalmiskiewicz.util.extensions.observeEvent

class PlanFragment() : Fragment() {

    private val planViewModel by viewModel<PlanViewModel>()
    private val mAdapter by inject<PlanAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlanBinding.inflate(inflater, container, false).apply {
            viewModel = planViewModel
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
        planViewModel.getAllPlan()
    }

    private fun initObservers() {
        observeEvent(planViewModel.event, ::handleEvent)
    }

    private fun handleEvent(event: PlanEvent?) {

    }
}