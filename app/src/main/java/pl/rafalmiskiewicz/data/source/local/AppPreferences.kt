package pl.rafalmiskiewicz.data.source.local

import com.chibatching.kotpref.KotprefModel
import pl.rafalmiskiewicz.common.CommonSettings.APP_PREFERENCES
import pl.rafalmiskiewicz.common.CommonSettings.NEW_MESSAGES_AMOUNT

class AppPreferences : KotprefModel() {

    override val kotprefName: String = APP_PREFERENCES
    var isUserLogged by booleanPref(default = false)
    var userLogin by stringPref(default = "")
    var userPassword by stringPref(default = "")
    var userToken by stringPref(default = "")
    var automaticLogOutTimestamp by longPref(0)
}