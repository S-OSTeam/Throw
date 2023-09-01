package com.example.throw_fornt.feature.store.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.viewModels
import com.example.throw_fornt.R
import com.example.throw_fornt.data.model.response.StoreModel
import com.example.throw_fornt.data.remote.retrofit.StoreRetrofit
import com.example.throw_fornt.databinding.ActivityRegisterBinding
import com.example.throw_fornt.feature.store.register.address.AddressActivity
import com.example.throw_fornt.feature.store.register.address.AddressResult
import com.example.throw_fornt.util.common.BindingActivity
import com.example.throw_fornt.util.common.Toaster

class RegisterActivity : BindingActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    private val viewModel: RegisterViewModel by viewModels()

    private var lat:String = ""
    private var lon:String = ""
    //StoreRetrofit 변수
    private val storeHelper: StoreRetrofit = StoreRetrofit()
    val REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.event.observe(this){handleEvent(it)}
    }

    private fun handleEvent(event: RegisterViewModel.Event){
        when(event){
            is RegisterViewModel.Event.Search -> searchAddress()
            is RegisterViewModel.Event.Register -> register(event.register)
            is RegisterViewModel.Event.Fail -> Toaster.showLong(this@RegisterActivity, event.msg)
        }
    }

    private fun register(data: StoreModel){
        val res = StoreModel(data.storePhone,lat,lon,data.bno,data.zipCode,data.fullAddress,data.storeName,data.trashType,"","","")
        storeHelper.registerResponse(res)
    }

    //주소검색 함수
    private fun searchAddress(){
        val intent: Intent = Intent(this, AddressActivity::class.java)
        //AddressActivity와 쌍방향을 데이터를 전달받기위해서 startActivityForResult를 사용
        startActivityForResult(intent, REQUEST_CODE)
    }

    //AddressActivity에서 전달받는 값을 처리해준다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            val receiveData = data?.getParcelableExtra<AddressResult>("data")
            if(receiveData!=null){
                binding.addressEdit.setText(receiveData.address.toString())
                binding.addressEdit2.setText(receiveData.zoneNo.toString())
                lat = receiveData.lat.toString()
                lon = receiveData.long.toString()
            }
        }
    }
}