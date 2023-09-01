package com.example.throw_fornt.feature.store

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.throw_fornt.R
import com.example.throw_fornt.data.model.response.StoreModel

//RecyclerView를 list_view_item.xml을 연결해주기 위한 adapter이다
class StoreAdapter(private var items: ArrayList<StoreModel>, val onClick: (StoreModel)->Unit): RecyclerView.Adapter<StoreAdapter.StoreViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_view_item, parent,false)
        return StoreViewHolder(view)
    }

    //list_view_item.xml과 연동된 TextView의 값을 리스트 마다 지정해줌
    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.storeName.text = items[position].storeName
        holder.storeType.text = items[position].trashType
        holder.itemView.setOnClickListener{
            onClick(items[position])
        }
    }

    fun updateList(list: ArrayList<StoreModel>){
        items = list
        notifyDataSetChanged()
    }

    //items의 갯수를 반환하여 RecyclerView에 나타나는 총 갯수를 보여줌
    override fun getItemCount(): Int {
        return items.size
    }

    //list_view_item.xml의 TextView를 연동시켜줌
    inner class StoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val storeName = itemView.findViewById<TextView>(R.id.store_name)
        val storeType = itemView.findViewById<TextView>(R.id.store_type)
    }
}