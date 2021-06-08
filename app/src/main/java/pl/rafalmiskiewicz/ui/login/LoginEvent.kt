package pl.rafalmiskiewicz.ui.login

import pl.rafalmiskiewicz.util.Event.SpecificEvent

sealed class LoginEvent : SpecificEvent {
    class Login(val login: String, val password: String) : LoginEvent()
    object MoveToHours: LoginEvent()
    object LogOut: LoginEvent()
}