package pl.rafalmiskiewicz.ui.transaction

import pl.rafalmiskiewicz.util.Event.SpecificEvent

sealed class TransactionEvent : SpecificEvent {
    class OpenMap(val addressGoogle: String) : TransactionEvent()
}