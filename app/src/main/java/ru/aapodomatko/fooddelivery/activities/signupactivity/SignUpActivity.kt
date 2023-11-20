package ru.aapodomatko.fooddelivery.activities.signupactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.activities.locationactivity.LocationActivity
import ru.aapodomatko.fooddelivery.activities.loginactivity.LoginActivity
import ru.aapodomatko.fooddelivery.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alreadyHaveAcc.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        binding.btnCreateAcc.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}