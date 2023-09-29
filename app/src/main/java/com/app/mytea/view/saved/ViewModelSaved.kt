package com.app.mytea.view.saved

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mytea.data.model.response.DataXXXXXXX
import com.app.mytea.data.model.response.ResponseDeleteSaved
import com.app.mytea.data.model.response.ResponseGetSavedData
import com.app.mytea.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelSaved: ViewModel() {
    private val saved = MutableLiveData<List<DataXXXXXXX>?>()
    private val delete = MutableLiveData<ResponseDeleteSaved?>()
    private val filteredSaved = MutableLiveData<List<DataXXXXXXX>?>()

    fun allLiveDataSaved(): MutableLiveData<List<DataXXXXXXX>?> {
        return saved
    }

    fun allLiveDataDelete(): MutableLiveData<ResponseDeleteSaved?> {
        return delete
    }

    fun getFilteredLiveData(): MutableLiveData<List<DataXXXXXXX>?> {
        return filteredSaved
    }

    fun getDataSaved(token: String) {
        ApiClient.instance.getSaved("Bearer $token")
            .enqueue(object : Callback<ResponseGetSavedData> {
                override fun onResponse(
                    call: Call<ResponseGetSavedData>,
                    response: Response<ResponseGetSavedData>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        saved.postValue(response.body()?.data)
                    } else {
                        saved.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetSavedData>, t: Throwable) {
                    saved.postValue(null)
                }

            })
    }


    fun deleteSaved(token: String, id: String) {
        ApiClient.instance.deleteSaved("Bearer $token", id)
            .enqueue(object : Callback<ResponseDeleteSaved> {
                override fun onResponse(
                    call: Call<ResponseDeleteSaved>,
                    response: Response<ResponseDeleteSaved>
                ) {
                    Log.d("TAG", "onResponse: ${response.body()?.data}")

                    if (response.isSuccessful) {
                        delete.postValue(response.body())
                    } else {
                        delete.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDeleteSaved>, t: Throwable) {
                    delete.postValue(null)
                }

            })
    }

    fun searchSavedItems(query: String, allItems: List<DataXXXXXXX>?) {
        if (allItems != null) {
            val filteredList = allItems.filter { item ->
                item.product?.name?.contains(query, ignoreCase = true) == true
            }
            filteredSaved.postValue(filteredList)
        }
    }

}