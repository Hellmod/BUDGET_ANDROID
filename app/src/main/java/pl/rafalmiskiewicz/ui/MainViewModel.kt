package pl.rafalmiskiewicz.ui

import pl.rafalmiskiewicz.ui.base.BaseViewModel
import pl.rafalmiskiewicz.ui.login.LoginEvent

class MainViewModel : BaseViewModel<MainEvent>() {

    fun onPlanClicked() {
        sendEvent(MainEvent.MoveToPlan)
    }
}