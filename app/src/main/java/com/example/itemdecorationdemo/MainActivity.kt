package com.example.itemdecorationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dataList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun initView() {
        list_rv.apply {
            adapter = SimpleAdapter()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration())
        }

        list_rv.addItemDecoration(GroupItemDecoration(this))
    }

    private fun initData() {
        dataList.add("苹果")
        dataList.add("梨子")
        dataList.add("桃子")
        dataList.add("西瓜")
        dataList.add("橘子")
        dataList.add("青菜")
        dataList.add("番茄")
        dataList.add("白菜")
        dataList.add("生菜")
        dataList.add("茄子")
        for(i in 0 until 10) {
            dataList.add(i.toString())
        }
    }


    inner class SimpleAdapter: RecyclerView.Adapter<SimpleAdapter.MyViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.title.text = dataList[position]
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.item_tv)
        }

    }

}