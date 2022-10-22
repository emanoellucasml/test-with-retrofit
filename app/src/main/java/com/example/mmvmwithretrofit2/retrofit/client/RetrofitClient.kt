package com.example.mmvmwithretrofit2.retrofit.client


/*
 * The class OkHttpClient isn't part of retrofit library, but it's what connects to the Internet
 * */
import android.util.Log
import com.datadog.android.DatadogEventListener
import com.datadog.android.DatadogInterceptor
import com.example.mmvmwithretrofit2.retrofit.service.PostService
import okhttp3.Interceptor
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){


    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"
        private lateinit var http: OkHttpClient.Builder

        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            http = OkHttpClient.Builder()
                .addInterceptor(DatadogInterceptor())
                .eventListenerFactory(DatadogEventListener.Factory())

            http.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val req = chain.request()
                        .newBuilder()
                        .addHeader("emanoel-teste1", "kk")
                        .build()
                    return chain.proceed(req)
                }
            })

            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return INSTANCE
        }


        fun createPostService(): PostService {
            return getRetrofitInstance().create(PostService::class.java)
        }


        fun <T> createService(service: Class<T>) : T{
            return getRetrofitInstance().create(service)
        }

        fun getInterceptors(){
            for(i in http.interceptors()){
                Log.d("emanoel", i.toString())
            }
        }

        fun addInterceptor(interceptor: Interceptor){
            http.addInterceptor(interceptor)
        }
    }


}