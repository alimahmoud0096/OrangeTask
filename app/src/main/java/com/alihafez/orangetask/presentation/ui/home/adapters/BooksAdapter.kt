package com.alihafez.orangetask.presentation.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.alihafez.orangetask.databinding.ItemBookBinding
import com.alihafez.orangetask.data.model.BookItem
import com.bumptech.glide.Glide
import javax.inject.Inject


class BooksAdapter @Inject constructor() : RecyclerView.Adapter<BooksAdapter.ItemViewHolder>() {

    var bookList: List<BookItem> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var onBookClicked: (ImageView, String, BookItem) -> Unit


    inner class ItemViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {


        with(holder) {
            binding.item=bookList[position]
            binding.executePendingBindings()
            //we can use this insteed of databinding
            /*with(bookList[position]) {
                binding.title.text = this.volumeInfo.title
                binding.author.text = volumeInfo.authors?.joinToString(",")
                Glide.with(itemView).load(this.volumeInfo.imageLinks?.smallThumbnail)
                    .into(binding.thumbnail)
            }*/

            binding.thumbnail.transitionName="hero_image_${position}"

            binding.root.setOnClickListener {
                if (::onBookClicked.isInitialized)
                    onBookClicked(binding.thumbnail,binding.thumbnail.transitionName,bookList[position])
            }

//            ViewCompat.setTransitionName(binding.thumbnail, "thumbnail")

        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}