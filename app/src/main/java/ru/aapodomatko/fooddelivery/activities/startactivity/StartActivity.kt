package ru.aapodomatko.fooddelivery.activities.startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.activities.loginactivity.LoginActivity
import ru.aapodomatko.fooddelivery.databinding.ActivityLoginBinding
import ru.aapodomatko.fooddelivery.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goUserLoginBt.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }





}