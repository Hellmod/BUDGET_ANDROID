package pl.rafalmiskiewicz.common

fun String.removeDateSuffixIfExists(): String {
    val dotIndex = this.indexOfFirst { it == '.' }
    var date = this
    if (dotIndex != -1) {
        date = date.substring(0, dotIndex)
    }
    val plusIndex = date.indexOfFirst { it == '+' }
    if (plusIndex != -1) {
        date = date.substring(0, plusIndex)
    }
    val cityStart = date.indexOfFirst { it == '[' }
    if (cityStart != -1) {
        date = date.substring(0, cityStart)
    }
    return date
}