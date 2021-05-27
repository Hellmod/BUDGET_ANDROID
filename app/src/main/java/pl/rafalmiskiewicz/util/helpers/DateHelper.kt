package pl.rafalmiskiewicz.util.helpers

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

object DateHelper {

    private const val PATTERN_DATE = "dd.MM.yyyy"
    private const val PATTERN_TIME = "HH:mm"

    fun parseDate(date: Date): String {
        val time = convertToLocalDateTimeViaInstant(date)
        return formatDate(time, PATTERN_DATE)
    }

    fun parseHour(date: Date): String {
        val time = convertToLocalDateTimeViaInstant(date)
        return formatDate(time, PATTERN_TIME)
    }

    private fun formatDate(date: LocalDateTime?, pattern: String): String {
        if (date != null) {
            return DateTimeFormatter.ofPattern(pattern, Locale.getDefault()).format(date)
        }
        return ""
    }

    private fun convertToLocalDateTimeViaInstant(dateToConvert: Date): LocalDateTime? {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }
}