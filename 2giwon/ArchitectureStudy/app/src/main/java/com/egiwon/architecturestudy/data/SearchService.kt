package com.egiwon.architecturestudy.data

import android.util.Log
import com.egiwon.architecturestudy.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchService(
    val receiver: SearchCallback
) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/v1/")
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ContentsService::class.java)

    fun getContentsList(
        query: String,
        type: String
    ) {
        retrofit.getContentsInfo(
            API_ID,
            API_SECRET,
            type,
            query
        ).enqueue(object : Callback<Content> {
            override fun onFailure(call: Call<Content>, t: Throwable) {
                if (BuildConfig.DEBUG) {
                    Log.d("RetroFit", "onFailure ${t.message}")
                }
            }

            override fun onResponse(
                call: Call<Content>,
                response: Response<Content>
            ) {
                response.body()?.let {
                    receiver.onSuccess(it.items)
                }
            }
        })
    }

    companion object {
        private const val API_ID = "N6fr8OFPNCzX7SVctnkG"
        private const val API_SECRET = "WHmQ8WtbYf"
    }
}