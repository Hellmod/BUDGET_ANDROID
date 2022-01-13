package pl.rafalmiskiewicz.ui

import pl.rafalmiskiewicz.ui.login.LoginEvent
import pl.rafalmiskiewicz.util.Event.SpecificEvent

sealed class MainEvent : SpecificEvent {
    object MoveToPlan: MainEvent()
}