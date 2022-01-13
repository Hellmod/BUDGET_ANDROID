package pl.rafalmiskiewicz.ui

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.rafalmiskiewicz.R
import pl.rafalmiskiewicz.data.source.local.AppPreferences
import pl.rafalmiskiewicz.databinding.ActivityMainBinding
import pl.rafalmiskiewicz.ui.login.LoginEvent
import pl.rafalmiskiewicz.util.notification.NotificationService.Companion.INTENT_ACTION_SEND_MESSAGE

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private val appPreferences: AppPreferences by inject()

    private var currentNavController: LiveData<NavController>? = null
    private val mViewModel by viewModel<MainViewModel>()

    lateinit var receiver: BroadcastReceiver

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

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val message = intent.getStringExtra("message")

                AlertDialog.Builder(this@MainActivity)
                    .setTitle(R.string.warning)
                    .setMessage(message)
                    .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.ok, R.string.info)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miItem1 ->
                    findNavController(R.id.nav_host_fragment).navigate(R.id.loginFragment)
                R.id.miItem2 ->
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_loginFragment_to_transactionFragment)
                R.id.miItem3 ->
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_loginFragment_to_planFragment)

            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    fun getToken(): String? {
        var out: String? = null
/*        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("RMRM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            out=token.toString()
            val msg = token.toString()
            if (token != null) {
                Log.d("RMRM", token)
            }
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()

        })*/
        out = "OK"
        return out
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(INTENT_ACTION_SEND_MESSAGE)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

}