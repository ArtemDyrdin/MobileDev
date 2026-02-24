package com.dyrdin.phones

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.core.content.ContextCompat

class RegistrationActivity : AppCompatActivity() {

    private var isEmailMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val btnByNumber = findViewById<Button>(R.id.button)
        val btnByEmail = findViewById<Button>(R.id.button2)
        val etRegisterField = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val etPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.editTextTextPassword2)
        val btnRegister = findViewById<Button>(R.id.button3)

        changeEnterMode(btnByEmail, btnByNumber, etRegisterField)

        btnByEmail.setOnClickListener {
            isEmailMode = true
            changeEnterMode(btnByEmail, btnByNumber, etRegisterField)
        }

        btnByNumber.setOnClickListener {
            isEmailMode = false
            changeEnterMode(btnByEmail, btnByNumber, etRegisterField)
        }

        btnRegister.setOnClickListener {
            val loginInput = etRegisterField.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (isEmailMode) {
                if (!loginInput.contains("@")) {
                    Toast.makeText(this, "Email должен содержать символ @", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (!loginInput.contains("+")) {
                    Toast.makeText(this, "Номер телефона должен содержать символ +", Toast.LENGTH_SHORT).show()
                }
            }

            if (password.length < 8) {
                Toast.makeText(this, "Пароль должен содержать минимум 8 символов", Toast.LENGTH_SHORT).show()
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeEnterMode(btnByEmail: Button, btnByNumber: Button, etRegisterField: EditText) {
        val activeColor = ContextCompat.getColor(this, R.color.purple_200)
        val inactiveColor = Color.parseColor("#757575")

        if (isEmailMode) {
            btnByEmail.setTextColor(activeColor)
            btnByNumber.setTextColor(inactiveColor)

            etRegisterField.hint = "Введите email"
            etRegisterField.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        } else {
            btnByNumber.setTextColor(activeColor)
            btnByEmail.setTextColor(inactiveColor)

            etRegisterField.hint = "Введите номер телефона"
            etRegisterField.inputType = InputType.TYPE_CLASS_PHONE
        }
        etRegisterField.text.clear()
    }
}