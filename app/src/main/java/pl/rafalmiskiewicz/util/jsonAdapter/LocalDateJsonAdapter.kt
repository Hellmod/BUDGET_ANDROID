package pl.rafalmiskiewicz.util.jsonAdapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import pl.rafalmiskiewicz.common.removeDateSuffixIfExists
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateJsonAdapter {
    companion object {
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val calendarFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }

    @ToJson
    fun timeToJson(date: LocalDate): String {
        return timeFormatter.format(date)
    }

    @FromJson
    fun jsonToTime(str: String): LocalDate {
        return try {
            LocalDate.parse(str, timeFormatter)
        } catch (e: Exception) {
            LocalDate.parse(str.removeDateSuffixIfExists(), LocalDateTimeJsonAdapter.timeFormatter)
        }
    }
}