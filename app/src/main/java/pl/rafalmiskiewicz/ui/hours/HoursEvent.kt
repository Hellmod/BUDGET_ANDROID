package pl.rafalmiskiewicz.ui.hours

import pl.rafalmiskiewicz.util.Event.SpecificEvent

sealed class HoursEvent : SpecificEvent {
    class Login(val login: String, val password: String) : HoursEvent()
}