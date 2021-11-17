package pl.rafalmiskiewicz.ui.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.rafalmiskiewicz.data.api.Plan
import pl.rafalmiskiewicz.databinding.ItemPlanBinding
import pl.rafalmiskiewicz.ui.base.*
import pl.rafalmiskiewicz.ui.base.BaseHolder
import pl.rafalmiskiewicz.util.helpers.DateHelper.parseDate
import pl.rafalmiskiewicz.util.helpers.DateHelper.parseHour
import kotlin.properties.Delegates

class PlanAdapter : BaseAdapter<Plan>(), AutoUpdatableAdapter {

    override var items: List<Plan> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id_plan == n.id_plan }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Plan> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlanBinding.inflate(inflater, parent, false)
        return FavouriteHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder<Plan>, position: Int) {
        holder.bind(items[position], clickListener)
    }

    class FavouriteHolder(val binding: ItemPlanBinding) : BaseHolder<Plan>(binding) {

        override fun bind(item: Plan, clickListener: OnRecyclerListener?) {

            binding.apply {
                itemPlanAmount.text = item.amount
                itemPlanDay.text = parseDate(item.date)
                itemPlanDescription.text = item.description
                itemPlanHour.text = parseHour(item.date)
            }

        }

    }
}