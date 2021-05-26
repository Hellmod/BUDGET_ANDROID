package pl.rafalmiskiewicz.ui.hours

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.rafalmiskiewicz.data.api.Hours
import pl.rafalmiskiewicz.databinding.ItemHoursBinding
import pl.rafalmiskiewicz.ui.base.*
import pl.rafalmiskiewicz.ui.base.BaseHolder
import kotlin.properties.Delegates

class HoursAdapter : BaseAdapter<Hours>(), AutoUpdatableAdapter {

    override var items: List<Hours> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id_hours == n.id_hours }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Hours> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHoursBinding.inflate(inflater, parent, false)
        return FavouriteHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder<Hours>, position: Int) {
        //holder.bind(items[position], clickListener)
    }

    class FavouriteHolder(val binding: ItemHoursBinding) : BaseHolder<Hours>(binding) {

        override fun bind(item: Hours, clickListener: OnRecyclerListener?) {

            binding.apply {
                itemHoursDay.text = item.hour_from.toString()
            }

        }

    }
}