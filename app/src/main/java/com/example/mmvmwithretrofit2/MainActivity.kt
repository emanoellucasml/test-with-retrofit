package com.example.mmvmwithretrofit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
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

        (findViewById<View>(R.id.btn_click) as View).setOnClickListener {
            val intent = Intent(this, UserName::class.java)
            startActivity(intent)
        }

        (findViewById<View>(R.id.btn_cause_crash) as View).setOnClickListener {
            val result = 120/0
        }

    }

    fun debug(){
        //RetrofitClient.getInterceptors()
    }
}