package ru.aapodomatko.fooddelivery.adapters.homeFragmentAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.aapodomatko.fooddelivery.databinding.HomeFoodItemBinding
import ru.aapodomatko.fooddelivery.models.PopularFoodModel

class HomeFragmentAdapter(
    val context: Context,
    val list : ArrayList<PopularFoodModel>
) : RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeFragmentAdapter.HomeFragmentViewHolder {
        val binding = HomeFoodItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeFragmentAdapter.HomeFragmentViewHolder,
        position: Int
    ) {
        val listModel = list[position]

        holder.foodName.text = listModel.getFoodName()
        holder.foodPrice.text = listModel.getFoodPrice()
        listModel.getFoodImage()?.let { holder.foodImage.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class HomeFragmentViewHolder(binding: HomeFoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val foodImage = binding.homeFoodImage
        val foodName = binding.homeFoodName
        val foodPrice = binding.homeFoodPrice
    }

}