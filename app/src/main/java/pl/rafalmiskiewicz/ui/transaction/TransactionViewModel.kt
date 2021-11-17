package pl.rafalmiskiewicz.ui.transaction

import androidx.lifecycle.MutableLiveData
import pl.rafalmiskiewicz.data.api.Transaction
import pl.rafalmiskiewicz.ui.base.BaseViewModel
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionViewModel(private val repository: MainRepository) : BaseViewModel<TransactionEvent>() {

    val transactionList = MutableLiveData<List<Transaction>>()
    val transactionListString = MutableLiveData<String>()

    fun getAllTransaction() {

        val response = repository.getAllTransaction()
        response.enqueue(object : Callback<List<Transaction>> {
            override fun onResponse(
                call: Call<List<Transaction>>,
                response: Response<List<Transaction>>
            ) {
                transactionList.postValue(response.body())
                transactionListString.value = transactionList.value?.toString()
            }

            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                transactionListString.postValue(t.message)
            }
        })
    }

}