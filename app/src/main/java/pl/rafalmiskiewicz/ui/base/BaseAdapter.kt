package pl.rafalmiskiewicz.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseHolder<T>>() {

    protected open var items: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    protected var clickListener: OnRecyclerListener? = null

    fun setData(items: List<T>) {
        this.items = items
    }

    fun setListener(listener: OnRecyclerListener) {
        this.clickListener = listener
    }

    override fun getItemCount(): Int = items.size
}