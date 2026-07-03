package com.yazilim.chronolock

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yazilim.chronolock.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvGoToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.btnRegister.setOnClickListener {

            val username = binding.etRegisterUsername.text.toString().trim()
            val email = binding.etRegisterEmail.text.toString().trim()
            val password = binding.etRegisterPassword.text.toString().trim()


            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Alanlar boşsa kodun aşağıya devam etmesini engelle
            }


            RetrofitClient.instance.registerUser(username, email, password)
                .enqueue(object : Callback<AuthResponse> {


                    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                        if (response.isSuccessful && response.body() != null) {
                            val apiResponse = response.body()!!

                            if (apiResponse.status == "success") {

                                Toast.makeText(this@RegisterActivity, apiResponse.message, Toast.LENGTH_LONG).show()
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {

                                Toast.makeText(this@RegisterActivity, apiResponse.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }


                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                        Toast.makeText(this@RegisterActivity, "Sunucuya bağlanılamadı: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }
}