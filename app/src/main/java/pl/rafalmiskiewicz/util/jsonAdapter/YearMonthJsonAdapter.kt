package pl.rafalmiskiewicz.util.jsonAdapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class YearMonthJsonAdapter {
    companion object {

        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM")
    }

    @ToJson
    fun timeToJson(date: YearMonth): String {
        return timeFormatter.format(date)
    }

    @FromJson
    fun jsonToTime(str: String): YearMonth {
        return YearMonth.parse(str, timeFormatter)
    }
}