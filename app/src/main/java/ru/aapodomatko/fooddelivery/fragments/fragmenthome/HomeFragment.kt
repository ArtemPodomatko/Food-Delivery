package ru.aapodomatko.fooddelivery.fragments.fragmenthome

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.adapters.homeFragmentAdapter.HomeFragmentAdapter
import ru.aapodomatko.fooddelivery.adapters.imageSliderAdapter.ImageSliderAdapter
import ru.aapodomatko.fooddelivery.models.PopularFoodModel
import kotlin.math.abs


class HomeFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var sliderAdapter: ImageSliderAdapter
    private lateinit var imageList: ArrayList<Int>
    private lateinit var handler: Handler

    private lateinit var adapter: HomeFragmentAdapter
    private lateinit var listPopularFood: ArrayList<PopularFoodModel>
    private lateinit var homeRecyclerView: RecyclerView

    private val runnable = Runnable {
        viewPager.currentItem = viewPager.currentItem + 1
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = view.findViewById(R.id.image_slider)
        homeRecyclerView = view.findViewById(R.id.home_recycler_view)



        listPopularFood = ArrayList()
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_burger, "Sandwich", "$7"))
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_sandwich, "Momo", "$9"))
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_momo, "Burger", "$5"))
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_burger, "Sandwich", "$7"))
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_sandwich, "Momo", "$9"))
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_momo, "Burger", "$5"))
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_burger, "Sandwich", "$7"))
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_sandwich, "Momo", "$9"))
        listPopularFood.add(PopularFoodModel(R.drawable.pop_menu_momo, "Burger", "$5"))

        adapter = HomeFragmentAdapter(requireContext(), listPopularFood)
        homeRecyclerView.layoutManager = LinearLayoutManager(activity)
        homeRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setTransformer()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewPager.removeCallbacks(runnable)
                viewPager.postDelayed(runnable, 5000)
            }
        })

    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(runnable, 5000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    private fun setTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(10))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager.setPageTransformer(transformer)

    }

    private fun init() {
        imageList = ArrayList()
        sliderAdapter = ImageSliderAdapter(requireContext(), imageList, viewPager)
        handler = Handler(Looper.myLooper()!!)

        imageList.add(R.drawable.banner_1)
        imageList.add(R.drawable.banner_2)
        imageList.add(R.drawable.banner_3)
        imageList.add(R.drawable.banner_4)
        imageList.add(R.drawable.banner_5)
        imageList.add(R.drawable.banner_6)
        imageList.add(R.drawable.banner_7)
        imageList.add(R.drawable.banner_8)
        imageList.add(R.drawable.banner_9)
        imageList.add(R.drawable.banner_10)

        viewPager.adapter = sliderAdapter
        viewPager.offscreenPageLimit = 3
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

}