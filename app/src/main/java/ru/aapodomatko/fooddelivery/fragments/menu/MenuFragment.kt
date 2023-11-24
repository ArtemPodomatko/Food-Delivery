package ru.aapodomatko.fooddelivery.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.adapters.bottomSheetAdapter.BottomSheetAdapter
import ru.aapodomatko.fooddelivery.adapters.menuFragmentAdapter.MenuFragmentAdapter
import ru.aapodomatko.fooddelivery.models.PopularFoodModel


class MenuFragment : Fragment() {
    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuAdapter: MenuFragmentAdapter
    private lateinit var mViewModel: MenuFragmentViewModel
    private lateinit var iconBack: ImageView
    private lateinit var bottomSheetRecyclerView: RecyclerView
    private lateinit var bottomSheetAdapter: BottomSheetAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        mViewModel = ViewModelProvider(this,
            MenuFragmentViewModel.MenuFragmentViewModelFactory(requireActivity().application
            ))[MenuFragmentViewModel::class.java]

        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)
        menuAdapter = MenuFragmentAdapter()
        menuAdapter.setOnClickListener {
            mViewModel.selectedItem(it)
            showBottomSheet()
        }
        menuRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = menuAdapter
        }

        mViewModel.foodItemsLiveData.observe(viewLifecycleOwner) {
            menuAdapter.differ.submitList(it)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.foodItemsLiveData.observe(viewLifecycleOwner) {
            menuAdapter.differ.submitList(it)
        }
        iconBack = view.findViewById(R.id.menu_icon_back)
        iconBack.setOnClickListener {
            findNavController().popBackStack()
        }
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

}