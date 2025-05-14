package com.alihafez.orangetask.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.alihafez.orangetask.R
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(thumbimg : ImageView, url : String?){
        GlobalScope.launch(Dispatchers.Main) {
            Glide.with(thumbimg)
                .load(url)
//            .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(thumbimg)
        }

    }

    @JvmStatic
    @BindingAdapter("setAuthor")
    fun setAuthor(textView: TextView, authors : List<String>?){

        authors?.let {
            textView.text=authors.joinToString(", ")
        }
    }

}