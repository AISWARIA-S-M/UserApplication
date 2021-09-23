package com.ibm.usersapplication.retrofit


import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit


object RetrofitInstance {
    private const val baseUrl = "https://api.npoint.io/"
    private var interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
        val builder: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient
            .Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).addInterceptor(interceptor)
                .addNetworkInterceptor(
                    Interceptor { chain ->
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                        val request = requestBuilder.build()
                        val response = chain.proceed(request)
                        Log.e("request", request.headers.toString())
                        when (response.code) {
                            HttpURLConnection.HTTP_OK ->{
                                Log.d("response",response.toString())
                                return@Interceptor response}
                            HttpURLConnection.HTTP_UNAUTHORIZED ->{
                                Log.d("response",response.toString())
                            }
                                HttpURLConnection.HTTP_FORBIDDEN ->{
                                    Log.d("response",response.toString())
                                }
                            HttpURLConnection.HTTP_SERVER_ERROR  -> {
                                Log.i("data", "Sorry!!! Something went wrong")
                               Log.d("response",response.toString())
                            }
                        }
                        response
                    }

                )
                .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}