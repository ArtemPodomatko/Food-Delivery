package ru.aapodomatko.fooddelivery.adapters.menuFragmentAdapter

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
import ru.aapodomatko.fooddelivery.models.PopularFoodModel

class MenuFragmentAdapter : RecyclerView.Adapter<MenuFragmentAdapter.MenuFragmentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuFragmentAdapter.MenuFragmentViewHolder {
        return MenuFragmentViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.menu_food_item, parent, false
        ))
    }

    override fun onBindViewHolder(
        holder: MenuFragmentAdapter.MenuFragmentViewHolder,
        position: Int
    ) {
        val foodItem = differ.currentList[position]
        holder.itemView.apply {
            val itemImageView = findViewById<ImageView>(R.id.menu_image_view)
            val itemName = findViewById<TextView>(R.id.menu_food_name)
            val itemPrice = findViewById<TextView>(R.id.menu_food_price)
            Glide.with(this).load(foodItem.foodImage).into(itemImageView)
            itemName.text = foodItem.foodName
            itemPrice.text = foodItem.foodPrice
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
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

    private var onItemClickListener: ((PopularFoodModel) -> Unit)? = null

    fun setOnClickListener(listener: (PopularFoodModel) -> Unit) {
        onItemClickListener = listener
    }

    inner class MenuFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(differ.currentList[adapterPosition])
            }
        }
    }

}