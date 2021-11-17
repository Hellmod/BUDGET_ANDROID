package pl.rafalmiskiewicz.ui.transaction

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.rafalmiskiewicz.databinding.FragmentTransactionBinding

class TransactionFragment() : Fragment() {

    private val transactionViewModel by viewModel<TransactionViewModel>()
    private val mAdapter by inject<TransactionAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTransactionBinding.inflate(inflater, container, false).apply {
            viewModel = transactionViewModel
            lifecycleOwner = viewLifecycleOwner
            favouritesList.apply {
                adapter = mAdapter
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        transactionViewModel.getAllTransaction()
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