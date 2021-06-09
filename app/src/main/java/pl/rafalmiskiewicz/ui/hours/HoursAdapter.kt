package pl.rafalmiskiewicz.ui.hours

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.rafalmiskiewicz.data.api.Hours
import pl.rafalmiskiewicz.databinding.ItemHourBinding
import pl.rafalmiskiewicz.ui.base.*
import pl.rafalmiskiewicz.ui.base.BaseHolder
import pl.rafalmiskiewicz.util.helpers.DateHelper.parseDate
import pl.rafalmiskiewicz.util.helpers.DateHelper.parseHour
import kotlin.properties.Delegates

class HoursAdapter : BaseAdapter<Hours>(), AutoUpdatableAdapter {

    override var items: List<Hours> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id_hours == n.id_hours }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Hours> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHourBinding.inflate(inflater, parent, false)
        return FavouriteHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder<Hours>, position: Int) {
        holder.bind(items[position], clickListener)
    }

    class FavouriteHolder(val binding: ItemHourBinding) : BaseHolder<Hours>(binding) {

        override fun bind(item: Hours, clickListener: OnRecyclerListener?) {

            binding.apply {
                itemHoursDay.text = parseDate(item.hour_from)
                itemHoursHourFrom.text = parseHour(item.hour_from)
                itemHoursHourTo.text = parseHour(item.hour_to)
            }

        }

    }
}