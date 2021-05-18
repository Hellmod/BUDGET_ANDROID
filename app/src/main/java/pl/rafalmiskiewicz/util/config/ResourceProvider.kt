package pl.rafalmiskiewicz.util.config

import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String
    fun getString(@StringRes stringId: Int, param: String): String
    fun getString(@StringRes stringId: Int, vararg params: Any): String
    fun getStringArray(@ArrayRes stringArrayId: Int): Array<String>
    fun getDrawable(iconId: Int): Drawable?
}