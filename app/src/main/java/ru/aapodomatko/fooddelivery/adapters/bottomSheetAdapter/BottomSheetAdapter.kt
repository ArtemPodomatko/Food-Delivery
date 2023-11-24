package ru.aapodomatko.fooddelivery.adapters.bottomSheetAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.models.PopularFoodModel

class BottomSheetAdapter(private var foodItem: PopularFoodModel) : RecyclerView.Adapter<BottomSheetAdapter.BottomSheetViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomSheetAdapter.BottomSheetViewHolder {
        return BottomSheetViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.bottom_sheet_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: BottomSheetAdapter.BottomSheetViewHolder, position: Int) {
        holder.itemView.apply {
            val foodItemName = findViewById<TextView>(R.id.food_name)
            val foodItemImageView = findViewById<ImageView>(R.id.bottomSheet_im)
            Glide.with(this).load(foodItem.foodImage).into(foodItemImageView)
            foodItemImageView.clipToOutline = true
            foodItemName.text = foodItem.foodName
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateFoodItem(newFoodItem: PopularFoodModel) {
        this.foodItem = newFoodItem
        notifyDataSetChanged()
    }

    class BottomSheetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}