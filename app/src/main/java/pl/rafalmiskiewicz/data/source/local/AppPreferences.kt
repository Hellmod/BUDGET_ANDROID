package pl.rafalmiskiewicz.data.source.local

import com.chibatching.kotpref.KotprefModel

class AppPreferences : KotprefModel() {

    var isUserLogged by booleanPref(default = false)
    var userLogin by stringPref(default = "")
    var userPassword by stringPref(default = "")
    var userToken by stringPref(default = "")
}