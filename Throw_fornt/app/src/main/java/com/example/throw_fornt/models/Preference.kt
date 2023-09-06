package com.example.throw_fornt.models

import android.content.Context
import android.content.SharedPreferences

class Preference(context:Context) {

        private val preferences: SharedPreferences = context.getSharedPreferences("com.example.throw_fornt.tokenSharedpreference", Context.MODE_PRIVATE)

        fun getString(key: String, defValue: String):String{
            return preferences.getString(key,defValue).toString()
        }

        fun setString(key: String, defValue: String){
            preferences.edit().putString(key, defValue).apply()
        }

}