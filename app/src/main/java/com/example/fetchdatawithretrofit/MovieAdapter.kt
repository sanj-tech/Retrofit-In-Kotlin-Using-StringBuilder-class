package com.example.fetchdatawithretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(var context: Context,var moviList: List<MyDataItem>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.row_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.userId.text=moviList[position].userId.toString()
        holder.userTitle.text=moviList[position].title
    }

    override fun getItemCount(): Int {
        return moviList.size
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      var userId=itemView.findViewById<TextView>(R.id.txtUserID)
        var userTitle=itemView.findViewById<TextView>(R.id.txtUserTitle)
    }

}