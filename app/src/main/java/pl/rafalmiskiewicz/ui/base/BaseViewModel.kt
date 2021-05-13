package pl.rafalmiskiewicz.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.rafalmiskiewicz.util.errorhandling.Failure
import pl.rafalmiskiewicz.util.Event.Event
import pl.rafalmiskiewicz.util.Event.SpecificEvent

abstract class BaseViewModel<T : SpecificEvent> : ViewModel() {

    val progressVisibility = MutableLiveData(false)

    protected val mErrorEvent: MutableLiveData<Event<Failure>> = MutableLiveData()
    val errorEvent: LiveData<Event<Failure>> = mErrorEvent

    protected val mEvent: MutableLiveData<Event<T>> = MutableLiveData()
    val event: LiveData<Event<T>> = mEvent

    open fun onBackClicked() = Unit
    open fun onSearchClicked() = Unit
    open fun onScannerClicked() = Unit

    protected fun showProgress() {
        progressVisibility.value = true
    }

    protected fun hideProgress() {
        progressVisibility.value = false
    }

    fun sendEvent(event: T) {
        mEvent.value = Event(event)
    }

    fun sendError(event: Failure) {
        mErrorEvent.value = Event(event)
    }
}