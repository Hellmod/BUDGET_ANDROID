package pl.rafalmiskiewicz.ui.schedules

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.rafalmiskiewicz.data.api.Schedules
import pl.rafalmiskiewicz.databinding.ItemScheduleBinding
import pl.rafalmiskiewicz.ui.base.*
import pl.rafalmiskiewicz.util.helpers.DateHelper.parseDate
import pl.rafalmiskiewicz.util.helpers.DateHelper.parseHour
import kotlin.properties.Delegates

class SchedulesAdapter : BaseAdapter<Schedules>(), AutoUpdatableAdapter {

    override var items: List<Schedules> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id_schedule == n.id_schedule }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Schedules> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemScheduleBinding.inflate(inflater, parent, false)
        return FavouriteHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder<Schedules>, position: Int) {
        holder.bind(items[position], clickListener)
    }

    class FavouriteHolder(val binding: ItemScheduleBinding) : BaseHolder<Schedules>(binding) {

        override fun bind(item: Schedules, clickListener: OnRecyclerListener?) {

            binding.apply {
                itemSchedulesDay.text = parseDate(item.hour_from)
                itemSchedulesHourFrom.text = parseHour(item.hour_from)
                itemSchedulesHourTo.text = parseHour(item.hour_to)
                itemSchedulesDescription.text = item.places.description
                itemSchedulesNavigate.setOnClickListener{
                    clickListener?.onClick(RunMap,bindingAdapterPosition)
                }
            }

        }

    }
}