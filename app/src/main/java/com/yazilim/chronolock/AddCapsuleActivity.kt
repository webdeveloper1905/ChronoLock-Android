package com.yazilim.chronolock

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yazilim.chronolock.databinding.ActivityAddCapsuleBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class AddCapsuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCapsuleBinding
    private var selectedDateString = ""
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCapsuleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userId = intent.getIntExtra("USER_ID", -1)


        binding.btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->


                val formattedMonth = String.format("%02d", selectedMonth + 1)
                val formattedDay = String.format("%02d", selectedDay)

                selectedDateString = "$selectedYear-$formattedMonth-$formattedDay"
                binding.tvSelectedDate.text = "Seçilen Tarih: $selectedDateString"

            }, year, month, day)

            datePickerDialog.show()
        }


        binding.btnSaveCapsule.setOnClickListener {
            val title = binding.etCapsuleTitle.text.toString().trim()
            val message = binding.etCapsuleMessage.text.toString().trim()

            if (title.isEmpty() || message.isEmpty() || selectedDateString.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun ve tarih seçin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            RetrofitClient.instance.addCapsule(userId, title, message, selectedDateString)
                .enqueue(object : Callback<AuthResponse> {
                    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                        if (response.isSuccessful && response.body() != null) {
                            val apiResponse = response.body()!!

                            Toast.makeText(this@AddCapsuleActivity, apiResponse.message, Toast.LENGTH_SHORT).show()

                            if (apiResponse.status == "success") {
                                finish()
                            }
                        }
                    }

                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                        Toast.makeText(this@AddCapsuleActivity, "Hata: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}