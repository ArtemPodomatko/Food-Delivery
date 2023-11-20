package ru.aapodomatko.fooddelivery.activities.locationactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}