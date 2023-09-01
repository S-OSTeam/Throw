package com.example.throw_fornt.feature.store.management

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.throw_fornt.R
import com.example.throw_fornt.data.model.response.StoreModel
import com.example.throw_fornt.databinding.ActivityManagementBinding
import com.example.throw_fornt.util.common.BindingActivity

class ManagementActivity : BindingActivity<ActivityManagementBinding>(R.layout.activity_management) {
    private val viewModel: ManagementViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.event.observe(this) { handleEvent(it) }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("수정 및 삭제")
            .setMessage("수정할지 삭제할지 선택해주세요.")
            .setNegativeButton("수정",
                DialogInterface.OnClickListener{
                    dialog, id -> modify()
            })
            .setPositiveButton("삭제",
                DialogInterface.OnClickListener {
                    dialog, id -> remove()
            })
        builder.show()
    }

    fun modify(){
        val intent: Intent = getIntent()
        val receive = intent?.getParcelableExtra<StoreModel>("data")
        if(receive!=null) viewModel.selectItem(receive)
    }

    fun remove(){
        finish()
    }

    fun handleEvent(event: ManagementViewModel.Event){

    }
}