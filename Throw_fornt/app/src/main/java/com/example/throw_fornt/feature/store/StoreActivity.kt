package com.example.throw_fornt.feature.store

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.throw_fornt.R
import com.example.throw_fornt.data.model.response.StoreModel
import com.example.throw_fornt.databinding.ActivityStoreBinding
import com.example.throw_fornt.feature.store.management.ManagementActivity
import com.example.throw_fornt.feature.store.management.ManagementViewModel
import com.example.throw_fornt.feature.store.register.RegisterActivity
import com.example.throw_fornt.feature.store.register.RegisterViewModel
import com.example.throw_fornt.util.common.BindingActivity

class StoreActivity : BindingActivity<ActivityStoreBinding>(R.layout.activity_store) {
    private val viewModel: StoreViewModel by viewModels()
    private val managerViewModel: ManagementViewModel by viewModels()
    var items = ArrayList<StoreModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.event.observe(this) { changeActivity(it) }

        listData()
    }

    //내 가게정보를 받는 데이터를 adapter를 통해 RecyclerView에 item을 넣어줌
    fun listData(){
        var list = findViewById<RecyclerView>(R.id.store_list)

        //TODO 삭제 예정
        items.add(StoreModel("","02-820-0920","test1",37.4945402275658,126.95977107078,"000-00-00000","07027","상도동 509","101호","00100","",""))
        items.add(StoreModel("","02-820-0920","test2",37.4945402275658,126.95977107078,"000-00-00000","07027","상도동 509","102호","10100","","",))

        //TODO 가게 정보 조회 api 넣기
        val adapter = StoreAdapter(items, viewModel::service)
        adapter.notifyDataSetChanged()

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    //수정 및 삭제, 등록 화면으로 이동하기 위한 함수
    fun changeActivity(event: StoreViewModel.Event){
        when (event){
            is StoreViewModel.Event.Register -> register()
            is StoreViewModel.Event.Service -> storeService(event.item)
        }
    }

    private fun register(){
        val intent: Intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun storeService(storeItem: StoreModel){
        val intent: Intent = Intent(this, ManagementActivity::class.java)
        //ManagementActivity에 선택한 내 가게 data 전달
        intent.putExtra("data",storeItem)
        startActivity(intent)
    }
}