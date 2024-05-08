package com.example.submissionawalmdl3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val listUsers =MutableLiveData<ArrayList<ItemsItem>>()

    fun setUsers(query:String){
        ApiConfig.getApiService()
            .getUser(query)
            .enqueue(object : Callback<DataUser>{
                override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<DataUser>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getUsers(): LiveData<ArrayList<ItemsItem>>{
        return listUsers
    }
}