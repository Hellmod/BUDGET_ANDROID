package pl.rafalmiskiewicz.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.rafalmiskiewicz.R
import pl.rafalmiskiewicz.databinding.ActivityMainBinding
import pl.rafalmiskiewicz.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private val mViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply {
                    viewModel = mViewModel
                    lifecycleOwner = this@MainActivity
                }
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.loginFragment)

    }

}