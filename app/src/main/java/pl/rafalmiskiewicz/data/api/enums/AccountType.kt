package pl.rafalmiskiewicz.data.api.enums

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class AccountType {
    ADMIN,
    USER
}