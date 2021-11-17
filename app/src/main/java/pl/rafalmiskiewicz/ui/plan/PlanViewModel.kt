package pl.rafalmiskiewicz.ui.plan

import androidx.lifecycle.MutableLiveData
import pl.rafalmiskiewicz.data.api.Plan
import pl.rafalmiskiewicz.ui.base.BaseViewModel
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlanViewModel(private val repository: MainRepository) : BaseViewModel<PlanEvent>() {

    val planList = MutableLiveData<List<Plan>>()
    val movieListString = MutableLiveData<String>()

    fun getAllPlan() {

        val response = repository.getAllPlan()
        response.enqueue(object : Callback<List<Plan>> {
            override fun onResponse(call: Call<List<Plan>>, response: Response<List<Plan>>) {
                planList.postValue(response.body())
                movieListString.value = planList.value?.toString()
            }

            override fun onFailure(call: Call<List<Plan>>, t: Throwable) {
                movieListString.postValue(t.message)
            }
        })
    }

}