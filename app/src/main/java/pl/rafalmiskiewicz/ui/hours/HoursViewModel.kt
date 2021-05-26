package pl.rafalmiskiewicz.ui.hours

import androidx.lifecycle.MutableLiveData
import pl.rafalmiskiewicz.data.api.Hours
import pl.rafalmiskiewicz.ui.base.BaseViewModel
import pl.rafalmiskiewicz.util.api.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HoursViewModel(private val repository: MainRepository) : BaseViewModel<HoursEvent>() {

    val hoursList = MutableLiveData<List<Hours>>()
    val hoursListShow = MutableLiveData<List<Hours>>()
    val movieListString = MutableLiveData<String>()

    fun getAllHours() {

        val response = repository.getAllHours()
        response.enqueue(object : Callback<List<Hours>> {
            override fun onResponse(call: Call<List<Hours>>, response: Response<List<Hours>>) {
                hoursList.postValue(response.body())
                //hoursListShow.value=hoursList.value
                movieListString.value = hoursList.value?.toString()
            }

            override fun onFailure(call: Call<List<Hours>>, t: Throwable) {
                movieListString.postValue(t.message)
            }
        })
    }

}