package pl.rafalmiskiewicz.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseHolder<in T>(containerView: ViewBinding) :
    RecyclerView.ViewHolder(containerView.root) {
    abstract fun bind(item: T, clickListener: OnRecyclerListener? = null)
}