package pl.rafalmiskiewicz.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.rafalmiskiewicz.ui.base.BaseAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> setRecyclerData(recyclerView: RecyclerView, items: List<T>?) {
    if (recyclerView.adapter is BaseAdapter<*>) {
        items?.let {
            (recyclerView.adapter as BaseAdapter<T>).setData(it)
        }
    }
}
