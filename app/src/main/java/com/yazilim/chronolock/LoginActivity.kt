package com.yazilim.chronolock

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yazilim.chronolock.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPreferences = getSharedPreferences("ChronoLockPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("KEY_IS_LOGGED_IN", false)

        if (isLoggedIn) {

            val savedUserId = sharedPreferences.getInt("KEY_USER_ID", -1)
            val savedUsername = sharedPreferences.getString("KEY_USERNAME", "Kullanıcı")

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_ID", savedUserId)
            intent.putExtra("USERNAME", savedUsername)
            startActivity(intent)
            finish()
            return
        }

        binding.tvGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Lütfen kullanıcı adı ve şifreyi girin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            RetrofitClient.instance.loginUser(username, password)
                .enqueue(object : Callback<AuthResponse> {

                    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                        if (response.isSuccessful && response.body() != null) {
                            val apiResponse = response.body()!!

                            if (apiResponse.status == "success") {
                                Toast.makeText(this@LoginActivity, apiResponse.message, Toast.LENGTH_SHORT).show()



                                val editor = sharedPreferences.edit()
                                editor.putInt("KEY_USER_ID", apiResponse.userId ?: -1)
                                editor.putString("KEY_USERNAME", apiResponse.username)
                                editor.putBoolean("KEY_IS_LOGGED_IN", true) // Oturum açık bayrağı
                                editor.apply() // Belleğe fiziksel olarak yazdırıyoruz


                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.putExtra("USER_ID", apiResponse.userId)
                                intent.putExtra("USERNAME", apiResponse.username)

                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, apiResponse.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Bağlantı hatası: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }
}