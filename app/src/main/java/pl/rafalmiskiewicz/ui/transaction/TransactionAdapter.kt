package pl.rafalmiskiewicz.ui.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import pl.rafalmiskiewicz.data.api.Transaction
import pl.rafalmiskiewicz.databinding.ItemTransactionBinding
import pl.rafalmiskiewicz.ui.base.*
import pl.rafalmiskiewicz.util.helpers.DateHelper.parseDate
import pl.rafalmiskiewicz.util.helpers.DateHelper.parseHour
import kotlin.properties.Delegates

class TransactionAdapter : BaseAdapter<Transaction>(), AutoUpdatableAdapter {

    override var items: List<Transaction> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id_transaction == n.id_transaction }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Transaction> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater, parent, false)
        return FavouriteHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder<Transaction>, position: Int) {
        holder.bind(items[position], clickListener)
    }

    class FavouriteHolder(val binding: ItemTransactionBinding) : BaseHolder<Transaction>(binding) {

        override fun bind(item: Transaction, clickListener: OnRecyclerListener?) {

            binding.apply {
                itemTransactionAmount.text = item.amount
                itemTransactionDay.text = parseDate(item.date)
                itemTransactionDescription.text = item.description
                itemTransactionHour.text = parseHour(item.date)
            }

        }

    }
}