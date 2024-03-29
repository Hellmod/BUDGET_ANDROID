package pl.rafalmiskiewicz.ui.login

import pl.rafalmiskiewicz.util.Event.SpecificEvent

sealed class LoginEvent : SpecificEvent {
    class Login(val login: String, val password: String) : LoginEvent()
    object MoveToPlan: LoginEvent()
    object MoveToTransaction: LoginEvent()
    object LogOut: LoginEvent()
}