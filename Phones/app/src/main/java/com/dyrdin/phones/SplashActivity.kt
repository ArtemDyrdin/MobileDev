package com.dyrdin.phones

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Handler
import android.os.Looper

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            when {
                !PrefManager.isUserRegistered(this) -> {
                    startActivity(Intent(this, RegistrationActivity::class.java))
                }

                !PrefManager.isAutoLoginEnabled(this) -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }

                else -> {
                    startActivity(Intent(this, ContentActivity::class.java))
                }
            }

            finish()

        }, 2000)
    }
}