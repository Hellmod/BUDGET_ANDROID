package pl.rafalmiskiewicz.data.api

import java.util.*

data class Plan(
    val id_plan: Int,
    val amount: String,
    val date: Date,
    val description: String
)