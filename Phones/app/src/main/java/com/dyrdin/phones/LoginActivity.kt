package com.dyrdin.phones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.EditText
import android.widget.CheckBox

import android.content.Intent
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etLogin = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val etPassword = findViewById<EditText>(R.id.editTextTextPassword3)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val btnLogin = findViewById<Button>(R.id.button4)

        btnLogin.setOnClickListener {

            val inputLogin = etLogin.text.toString()
            val inputPassword = etPassword.text.toString()

            val savedLogin = PrefManager.getLogin(this)
            val savedPassword = PrefManager.getPassword(this)

            if (inputLogin == savedLogin && inputPassword == savedPassword) {

                PrefManager.setAutoLogin(this, checkBox.isChecked)

                startActivity(Intent(this, ContentActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}