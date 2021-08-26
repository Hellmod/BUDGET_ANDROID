package pl.rafalmiskiewicz.util.notification

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService() {

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        Log.d("RMRM", "The token refeshed:$newToken")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.data["message"]?.let {
            val messageRecived = it
            Log.d("RMRM", "Received message$messageRecived")

            passMessageToActivity(messageRecived)
        }
        passMessageToActivity("TSEST")
    }

    private fun passMessageToActivity(message: String) {
        val intent = Intent().apply {
            action = INTENT_ACTION_SEND_MESSAGE
            putExtra("message", message)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    companion object {

        const val INTENT_ACTION_SEND_MESSAGE = "INTENT_ACTION_SEND_MESSAGE"
    }
}