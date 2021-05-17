package pl.rafalmiskiewicz.common

object Validator {

    fun isValidEmail(text: String?): Boolean {
        text?.let {
            if (text.isNotBlank()) {
                return androidx.core.util.PatternsCompat.EMAIL_ADDRESS.matcher(text).matches()
            }
        }
        return false
    }

}