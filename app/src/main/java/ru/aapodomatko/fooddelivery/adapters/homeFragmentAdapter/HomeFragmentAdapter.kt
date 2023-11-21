package ru.aapodomatko.fooddelivery.adapters.homeFragmentAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aapodomatko.fooddelivery.R
import ru.aapodomatko.fooddelivery.databinding.HomeFoodItemBinding
import ru.aapodomatko.fooddelivery.models.PopularFoodModel

class HomeFragmentAdapter : RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeFragmentAdapter.HomeFragmentViewHolder {
        return HomeFragmentViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.home_food_item, parent, false
        ))
    }

    override fun onBindViewHolder(
        holder: HomeFragmentAdapter.HomeFragmentViewHolder,
        position: Int
    ) {
        val foodItem = differ.currentList[position]
        holder.itemView.apply {
            val foodItemName = findViewById<TextView>(R.id.home_food_name)
            val foodItemPrice = findViewById<TextView>(R.id.home_food_price)
            val foodItemImage = findViewById<ImageView>(R.id.home_food_image)
            Glide.with(this).load(foodItem.foodImage).into(foodItemImage)
            foodItemImage.clipToOutline = true
            foodItemName.text = foodItem.foodName
            foodItemPrice.text = foodItem.foodPrice
        }



    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((PopularFoodModel) -> Unit)? = null

    inner class HomeFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(differ.currentList[adapterPosition])
            }
        }
    }

    fun setOnItemClickListener(listener: (PopularFoodModel) -> Unit){
        onItemClickListener = listener
    }

    private val callback = object : DiffUtil.ItemCallback<PopularFoodModel>() {
        override fun areItemsTheSame(
            oldItem: PopularFoodModel,
            newItem: PopularFoodModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularFoodModel,
            newItem: PopularFoodModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)

}