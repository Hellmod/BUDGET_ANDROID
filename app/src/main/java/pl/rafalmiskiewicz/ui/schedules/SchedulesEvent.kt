package pl.rafalmiskiewicz.ui.schedules

import pl.rafalmiskiewicz.util.Event.SpecificEvent

sealed class SchedulesEvent : SpecificEvent {
    class Login(val login: String, val password: String) : SchedulesEvent()
}