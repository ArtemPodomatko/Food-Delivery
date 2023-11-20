package ru.aapodomatko.fooddelivery.activities.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.databinding.ActivityMainBinding
import ru.aapodomatko.fooddelivery.fragments.fragmentcart.CartFragment
import ru.aapodomatko.fooddelivery.fragments.fragmenthistory.HistoryFragment
import ru.aapodomatko.fooddelivery.fragments.fragmenthome.HomeFragment
import ru.aapodomatko.fooddelivery.fragments.fragmentprofile.ProfileFragment
import ru.aapodomatko.fooddelivery.fragments.fragmentsearh.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(HomeFragment())

        binding.bottomNavMenu.setOnItemSelectedListener {
            when ( it.itemId ) {
                R.id.menu_home -> {
                    changeFragment(HomeFragment())
                }
                R.id.menu_cart -> {
                    changeFragment(CartFragment())
                }
                R.id.menu_search -> {
                    changeFragment(SearchFragment())
                }
                R.id.menu_history -> {
                    changeFragment(HistoryFragment())
                }
                R.id.menu_user -> {
                    changeFragment(ProfileFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
