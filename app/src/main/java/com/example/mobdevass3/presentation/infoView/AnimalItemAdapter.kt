package com.example.mobdevass3.presentation.infoView

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdevass3.R
import com.example.mobdevass3.domain.entity.Animal
import com.example.mobdevass3.presentation.mainview.MyItemOnClickListener
import com.example.mobdevass3.utils.downloadImage
import com.example.mobdevass3.utils.inflate

class AnimalItemAdapter(private val items: ArrayList<Animal>, val clickListener: MyItemOnClickListener) :
    RecyclerView.Adapter<AnimalItemAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.findViewById(R.id.animal_pic)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val age: TextView = itemView.findViewById(R.id.age)
        private val breed: TextView = itemView.findViewById(R.id.breed)

        init {
            itemView.setOnClickListener { clickListener.onClick(items[layoutPosition]) }
        }

        fun bind(animal: Animal) {
            animal.photos?.let { photoList ->
                if (photoList.size > 0) {
                    photoList[0].small?.let {
                        photo.downloadImage(it)
                    }
                }
            }
            name.text = animal.name
            description.text = animal.description
            age.text = animal.age
            breed.text = animal.breed
        }
    }


    fun updateDate(newItems: List<Animal>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflatedView = parent.inflate(R.layout.animal_info_card, false)
        return ItemViewHolder(inflatedView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}