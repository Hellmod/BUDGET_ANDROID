package pl.rafalmiskiewicz.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import pl.rafalmiskiewicz.R
import pl.rafalmiskiewicz.databinding.ActivityMainBinding
import pl.rafalmiskiewicz.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    }

}