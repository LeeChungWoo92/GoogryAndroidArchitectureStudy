package com.ironelder.androidarchitecture.data.source

import com.ironelder.androidarchitecture.data.TotalModel
import com.ironelder.androidarchitecture.data.repository.SearchDataRepositoryImpl
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchDataSourceImpl : SearchDataSource {
    override fun getDataForSearch(
        type: String,
        query: String,
        success: (result: TotalModel) -> Unit,
        fail: (msg: String) -> Unit
    ) {
        SearchDataRepositoryImpl.getDataForSearch(type, query)
            .enqueue(object : Callback<TotalModel> {
                override fun onFailure(call: Call<TotalModel>, t: Throwable) {
                    t.message?.let(fail)
                }

                override fun onResponse(call: Call<TotalModel>, response: Response<TotalModel>) {
                    response.body()?.let(success)
                }

            })
    }

}