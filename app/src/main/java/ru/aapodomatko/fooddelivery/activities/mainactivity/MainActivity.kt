package ru.aapodomatko.fooddelivery.activities.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.databinding.ActivityMainBinding
import ru.aapodomatko.fooddelivery.fragments.fragmentcart.CartFragment
import ru.aapodomatko.fooddelivery.fragments.fragmenthistory.HistoryFragment
import ru.aapodomatko.fooddelivery.fragments.fragmenthome.HomeFragment
import ru.aapodomatko.fooddelivery.fragments.fragmentprofile.ProfileFragment
import ru.aapodomatko.fooddelivery.fragments.fragmentsearh.SearchFragment

class MainActivity : AppCompatActivity() {
    private  var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            _binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)
            mBinding.bottomNavMenu.setupWithNavController(
                navController = findNavController(R.id.fragment_container)
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
