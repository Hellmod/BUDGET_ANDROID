package pl.rafalmiskiewicz.ui.base

import pl.rafalmiskiewicz.ui.hours.ClickType

interface OnRecyclerListener {
    fun onClick(type: ClickType, pos:Int)
}