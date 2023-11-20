package ru.aapodomatko.fooddelivery.adapters.imageSliderAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ru.aapodomatko.fooddelivery.R

class ImageSliderAdapter(
    private val context: Context,
    private val imageList: ArrayList<Int>,
    private val viewPager: ViewPager2
) : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {
    inner class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image_in_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_container, parent, false)
        return ImageSliderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.image.setImageResource(imageList[position])

        if (position == imageList.size - 1) {
            viewPager.post(Runnable {
                imageList.addAll(imageList)
                notifyDataSetChanged()
            })
        }
    }
}