package pl.rafalmiskiewicz.ui.schedules

import pl.rafalmiskiewicz.util.Event.SpecificEvent

sealed class SchedulesEvent : SpecificEvent {
    class OpenMap(val addressGoogle: String) : SchedulesEvent()
}