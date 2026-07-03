package com.yazilim.chronolock

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yazilim.chronolock.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userId = intent.getIntExtra("USER_ID", -1)
        val username = intent.getStringExtra("USERNAME") ?: "Kullanıcı"


        binding.tvWelcome.text = "Merhaba, $username!"


        binding.rvCapsules.layoutManager = LinearLayoutManager(this)


        loadUserCapsules()


        binding.fabAddCapsule.setOnClickListener {
            val intent = Intent(this, AddCapsuleActivity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }


        binding.btnLogout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("ChronoLockPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.clear()
            editor.apply()

            Toast.makeText(this, "Oturum başarıyla kapatıldı.", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        loadUserCapsules()
    }

    private fun loadUserCapsules() {
        if (userId == -1) return

        RetrofitClient.instance.getCapsules(userId)
            .enqueue(object : Callback<CapsuleResponse> {

                override fun onResponse(call: Call<CapsuleResponse>, response: Response<CapsuleResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val apiResponse = response.body()!!

                        if (apiResponse.status == "success") {
                            val adapter = CapsuleAdapter(apiResponse.capsules)
                            binding.rvCapsules.adapter = adapter
                        } else {
                            Toast.makeText(this@MainActivity, "Kapsüller yüklenemedi.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<CapsuleResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Liste yükleme hatası: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }
}