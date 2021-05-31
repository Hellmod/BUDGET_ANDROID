package pl.rafalmiskiewicz.data.source.local

import com.scottyab.aescrypt.AESCrypt

class CredentialStore(
    private val appPreferences: AppPreferences
) {

    private val key = "jRbg3uNZAhQx2PyXZjMsRVU2UPQHm3"

    var isLogged: Boolean = false//appPreferences.isUserLogged//RM
        set(value) {
            //appPreferences.isUserLogged = value //RM
            field = value
        }
        get() = false//appPreferences.isUserLogged//RM

    fun store(login: String, password: String, token: String) {
        val encryptedLogin = AESCrypt.encrypt(key, login)
        val encryptedPassword = AESCrypt.encrypt(key, password)
        val encryptedToken = AESCrypt.encrypt(key, token)

        appPreferences.userLogin = encryptedLogin
        appPreferences.userPassword = encryptedPassword
        appPreferences.userToken = encryptedToken
    }

    fun restore(): Credentials {
        val encryptedLogin = appPreferences.userLogin
        val encryptedPassword = appPreferences.userPassword
        val encryptedToken = appPreferences.userToken

        val decryptedLogin =
            if (encryptedLogin.isNotBlank()) AESCrypt.decrypt(key, encryptedLogin) else ""
        val decryptedPassword =
            if (encryptedPassword.isNotBlank()) AESCrypt.decrypt(key, encryptedPassword) else ""
        val decryptedToken =
            if (encryptedToken.isNotBlank()) AESCrypt.decrypt(key, encryptedToken) else ""

        return Credentials(decryptedLogin, decryptedPassword, decryptedToken)
    }
}

data class Credentials(val login: String, val password: String, val token: String)