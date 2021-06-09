package pl.rafalmiskiewicz.data.api

import java.util.*

data class Schedules(
    val id_schedule: Int,
    val hour_from: Date,
    val hour_to: Date,
    val places: Places
)