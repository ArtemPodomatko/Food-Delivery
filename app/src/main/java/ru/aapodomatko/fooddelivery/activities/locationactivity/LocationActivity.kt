package ru.aapodomatko.fooddelivery.activities.locationactivity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.activities.mainactivity.MainActivity
import ru.aapodomatko.fooddelivery.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryList = arrayOf("Russia", "Brazil", "Portugal", "Turkey", "England")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countryList)

        binding.locationList.setAdapter(adapter)

        binding.locationList.setOnItemClickListener { parent, view, position, id ->
            val selectedLocation = parent.getItemAtPosition(position) as String
            showDialogAtPosition(selectedLocation)
        }


    }

    fun showDialogAtPosition(location: String) {
        val dialogView = layoutInflater.inflate(R.layout.alert_dialog, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogView.findViewById<AppCompatButton>(R.id.btn_yes).setOnClickListener {
            startActivityWithLocation(location)
            dialog.dismiss()
        }

        dialogView.findViewById<AppCompatButton>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun startActivityWithLocation(location: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("location", location)
        startActivity(intent)

    }
}