package pl.corelogic.supplify.util.extensions

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import pl.rafalmiskiewicz.R
import pl.rafalmiskiewicz.util.Event.Event
import pl.rafalmiskiewicz.util.errorhandling.Failure
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun View.clickWithThrottle(debounceTime: Long = 1000L, action: () -> Unit) {
    var lastClickTime: Long = 0

    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime >= debounceTime) action()

        lastClickTime = SystemClock.elapsedRealtime()
    }
}

inline fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(
    liveData: L,
    crossinline body: (T?) -> Unit
) {
    liveData.observe(this, Observer { body(it.getContentIfNotHandled()) })
}

inline fun <T : Failure, L : LiveData<Event<T>>> LifecycleOwner.observeFailure(
    liveData: L,
    crossinline body: (T?) -> Unit
) {
    liveData.observe(this, Observer { body(it.getContentIfNotHandled()) })
}

inline fun <T : Any> Fragment.observeNavigationResult(key: String, crossinline body: (T) -> Unit) {
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
        ?.observe(viewLifecycleOwner, Observer {
            body(it)
            findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(key)
        })
}

fun <T : Any> Fragment.setNavigationResult(key: String, item: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, item)
}

inline fun <T : Any> Fragment.observeDialogNavigationResult(
    key: String,
    crossinline body: (T) -> Unit
) {
    val navController = findNavController()
    val navBackStackEntry = navController.currentBackStackEntry ?: return
    navBackStackEntry.lifecycle.addObserver(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME && navBackStackEntry.savedStateHandle.contains(key)) {
            val result = navBackStackEntry.savedStateHandle.get<T>(key)
            if (result != null) {
                body(result)
                navBackStackEntry.savedStateHandle.remove<T>(key)
            }
        }
    })
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun <T1 : Any, T2 : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> Unit) {
    if (p1 != null && p2 != null) block(p1, p2)
}

fun Context.toast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

fun Context.toast(message: Int): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

fun Context.longToast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_LONG)
    .apply {
        show()
    }

fun Context.longToast(message: Int): Toast = Toast
    .makeText(this, message, Toast.LENGTH_LONG)
    .apply {
        show()
    }

fun Fragment.toast(message: CharSequence) {
    context?.toast(message)
}

fun Fragment.toast(message: Int) {
    context?.toast(message)
}

fun Fragment.longToast(message: CharSequence) {
    context?.longToast(message)
}

fun Fragment.longToast(message: Int) {
    context?.longToast(message)
}

fun <T> Flow<T>.distinct(): Flow<T> = flow {
    val past = mutableSetOf<T>()
    collect {
        val isNew = past.add(it)
        if (isNew) emit(it)
    }
}
