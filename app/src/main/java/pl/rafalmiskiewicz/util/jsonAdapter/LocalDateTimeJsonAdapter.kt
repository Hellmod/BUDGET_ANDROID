package pl.rafalmiskiewicz.util.jsonAdapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import pl.rafalmiskiewicz.common.removeDateSuffixIfExists
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeJsonAdapter {
    companion object {

        val hourFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    }

    @ToJson
    fun timeToJson(date: LocalDateTime): String {
        return LocalDateJsonAdapter.timeFormatter.format(date)
    }

    @FromJson
    fun jsonToTime(str: String): LocalDateTime {
        return LocalDateTime.parse(str.removeDateSuffixIfExists(), timeFormatter)
    }
}