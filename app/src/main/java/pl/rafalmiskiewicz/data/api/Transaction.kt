package pl.rafalmiskiewicz.data.api

import java.util.*

data class Transaction(
    val id_transaction: Int,
    val date: Date,
    val description: String,
    val amount: String
)
