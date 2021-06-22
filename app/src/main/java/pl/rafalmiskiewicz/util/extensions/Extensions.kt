package pl.rafalmiskiewicz.util.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import pl.rafalmiskiewicz.util.Event.Event

inline fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(
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