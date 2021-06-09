package pl.rafalmiskiewicz.ui.schedules

import androidx.lifecycle.MutableLiveData
import pl.rafalmiskiewicz.data.api.Schedules
import pl.rafalmiskiewicz.ui.base.BaseViewModel
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchedulesViewModel(private val repository: MainRepository) : BaseViewModel<SchedulesEvent>() {

    val schedulesList = MutableLiveData<List<Schedules>>()
    val schedulesListString = MutableLiveData<String>()

    fun getAllSchedules() {

        val response = repository.getAllSchedules()
        response.enqueue(object : Callback<List<Schedules>> {
            override fun onResponse(call: Call<List<Schedules>>, response: Response<List<Schedules>>) {
                schedulesList.postValue(response.body())
                schedulesListString.value = schedulesList.value?.toString()
            }

            override fun onFailure(call: Call<List<Schedules>>, t: Throwable) {
                schedulesListString.postValue(t.message)
            }
        })
    }

}