package com.dyrdin.phones

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.core.content.edit

import android.content.Intent

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

        val phoneRegex = Regex("""^\+\d{10,15}$""")
        val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$""")

        changeEnterMode(btnByEmail, btnByNumber, etRegisterField)

        btnByEmail.setOnClickListener {
            isEmailMode = true
            changeEnterMode(btnByEmail, btnByNumber, etRegisterField)
            etPassword.text.clear()
            etConfirmPassword.text.clear()
        }

        btnByNumber.setOnClickListener {
            isEmailMode = false
            changeEnterMode(btnByEmail, btnByNumber, etRegisterField)
            etPassword.text.clear()
            etConfirmPassword.text.clear()
        }

        btnRegister.setOnClickListener {

            val loginInput = etRegisterField.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            var isValid = true

            if (isEmailMode) {
                if (!emailRegex.matches(loginInput)) {
                    Toast.makeText(this, "Неверный email", Toast.LENGTH_SHORT).show()
                    isValid = false
                }
            } else {
                if (!phoneRegex.matches(loginInput)) {
                    Toast.makeText(this, "Неверный номер", Toast.LENGTH_SHORT).show()
                    isValid = false
                }
            }

            if (password.length < 8) {
                Toast.makeText(this, "Пароль минимум 8 символов", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (isValid) {

                PrefManager.saveUser(this, loginInput, password)
                PrefManager.setAutoLogin(this, false)

                startActivity(Intent(this, ContentActivity::class.java))
                finish()
            }
        }
    }

    private fun changeEnterMode(btnByEmail: Button, btnByNumber: Button, etRegisterField: EditText) {
        val activeColor = ContextCompat.getColor(this, R.color.purple_200)
        val inactiveColor = "#757575".toColorInt()

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