package com.example.fetchdatawithretrofit

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchdatawithretrofit.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: MovieAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    var Base_url = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       binding.movieRcv.setHasFixedSize(true)
        linearLayoutManager=LinearLayoutManager(this)
        binding.movieRcv.layoutManager=linearLayoutManager


        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Base_url)
                .build().create(ApiInterface::class.java)

        var retofitData = retrofitBuilder.getData()

        retofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>
            ) {

                val responsBody = response.body()!!

                myAdapter=MovieAdapter(baseContext,responsBody)
                myAdapter.notifyDataSetChanged()
                 binding.movieRcv.adapter=myAdapter





//                val myStringBuilder = StringBuilder()
//                for (data in responsBody) {
//                    myStringBuilder.append(data.id)
//                    myStringBuilder.append("\n")
//                    myStringBuilder.append(data.title)
//                    myStringBuilder.append(data.body)
//                    myStringBuilder.append(data.userId)
//                }
//                binding.txtId.text = myStringBuilder
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("MainAct", "On Failuar" + t.message)
            }
        })


    }
}