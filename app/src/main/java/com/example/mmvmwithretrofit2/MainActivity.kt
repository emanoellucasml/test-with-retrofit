package com.example.mmvmwithretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mmvmwithretrofit2.retrofit.client.RetrofitClient
import com.example.mmvmwithretrofit2.retrofit.entity.PersonEntity
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitClient.createPostService()

        val call: Call<List<PersonEntity>> = service.list()

        /*
         * Since a call to an API is an asynchronous statement, we can enqueue this snippet and pass a callback that will be called
         * when the results of the call are ready.
         * */
        call.enqueue(object : Callback<List<PersonEntity>> {
            override fun onResponse(call: Call<List<PersonEntity>>, response: Response<List<PersonEntity>>) {
                response?.body()?.first().let{
                    Toast.makeText(baseContext, "${it?.name} - ${it?.skinColor}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<PersonEntity>>, t: Throwable) {
                Toast.makeText(baseContext, "failure!", Toast.LENGTH_LONG).show()
            }
        })

        RetrofitClient.addInterceptor(object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val req = chain.request()
                    .newBuilder()
                    .addHeader("emanoel-teste2", "kk")
                    .build()
                return chain.proceed(req)
            }
        })

        RetrofitClient.addInterceptor(object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val req = chain.request()
                    .newBuilder()
                    .addHeader("emanoel-teste3", "kk")
                    .build()
                return chain.proceed(req)
            }
        })

        debug()
    }

    fun debug(){
        RetrofitClient.getInterceptors()
    }
}