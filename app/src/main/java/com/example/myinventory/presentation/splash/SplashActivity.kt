package com.example.myinventory.presentation.splash

import android.content.Intent
import android.os.Bundle
import com.example.myinventory.presentation.base.BaseActivity
import com.example.myinventory.presentation.home.HomeActivity


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

        finish()
    }

}