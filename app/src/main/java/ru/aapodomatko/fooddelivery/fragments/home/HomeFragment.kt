package ru.aapodomatko.fooddelivery.fragments.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.adapters.bottomSheetAdapter.BottomSheetAdapter
import ru.aapodomatko.fooddelivery.adapters.homeFragmentAdapter.HomeFragmentAdapter
import ru.aapodomatko.fooddelivery.adapters.imageSliderAdapter.ImageSliderAdapter
import ru.aapodomatko.fooddelivery.models.FoodItemsModel
import ru.aapodomatko.fooddelivery.models.PopularFoodModel
import kotlin.math.abs


class HomeFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var sliderAdapter: ImageSliderAdapter
    private lateinit var imageList: ArrayList<Int>
    private lateinit var handler: Handler
    private lateinit var foodAdapter: HomeFragmentAdapter
    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var mViewModel: HomeFragmentViewModel
    private lateinit var bottomSheetAdapter: BottomSheetAdapter
    private lateinit var bottomSheetRecyclerView: RecyclerView
    private lateinit var goMenu: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = view.findViewById(R.id.image_slider)
        mViewModel = ViewModelProvider(this,
            HomeFragmentViewModel.HomeFragmentViewModelFactory(requireActivity().application)
        )[HomeFragmentViewModel::class.java]


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setTransformer()
        addFoodItems()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewPager.removeCallbacks(runnable)
                viewPager.postDelayed(runnable, 5000)
            }
        })
        homeRecyclerView = view.findViewById(R.id.home_recycler_view)
        foodAdapter = HomeFragmentAdapter()
        foodAdapter.setOnItemClickListener {
            mViewModel.selectedItem(it)
            showBottomSheet()
        }

        homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = foodAdapter
        }

        goMenu = view.findViewById(R.id.go_menu)
        goMenu.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_menuFragment)
        }

        mViewModel.foodItemsLiveData.observe(viewLifecycleOwner) { foodItems ->
            foodAdapter.differ.submitList(foodItems)

        }


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

    private val runnable = Runnable {
        viewPager.currentItem = viewPager.currentItem + 1
    }


    private fun showBottomSheet() {
        val bottomSheet = layoutInflater.inflate(R.layout.bottom_sheet, null)
        val foodItem = PopularFoodModel()
        val dialog = BottomSheetDialog(requireContext(), R.style.FullScreenBottomSheetDialogTheme)
        dialog.setOnShowListener { it ->
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            parentLayout?.let {
                val behavior = BottomSheetBehavior.from(it)
                setUpFullHeight(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }


        dialog.setContentView(bottomSheet)
        dialog.setCancelable(true)
        setUpFullHeight(bottomSheet)
        dialog.show()

        bottomSheetRecyclerView = bottomSheet.findViewById(R.id.bottom_sheet_recyclerView)
        bottomSheetAdapter = BottomSheetAdapter(foodItem)
        bottomSheetRecyclerView.apply {
            adapter = bottomSheetAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        mViewModel.selectedFoodItem.observe(viewLifecycleOwner)  {
            bottomSheetAdapter.updateFoodItem(it)
        }


    }

    private fun setUpFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun addFoodItems() {
        val listFoodItems = FoodItemsModel().createItems()
        for (foodItem in listFoodItems) {
            mViewModel.addFoodItem(foodItem)
        }

    }


}