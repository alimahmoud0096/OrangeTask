package com.alihafez.orangetask.presentation.ui.bookDetails

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.alihafez.orangetask.R
import com.alihafez.orangetask.databinding.FragmentBookDetailsBinding
import com.bumptech.glide.Glide

class BookDetailsFragment : Fragment() {

    private var _binding: FragmentBookDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val args by navArgs<BookDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        init()
        return root
    }

    private fun init() {

        binding.thumbnailDetails.transitionName=args.transitionName
        binding.item=args.bookItem
        binding.lifecycleOwner=this
        binding.executePendingBindings()

        //we can use this insteed of binding
        /*with(args.bookItem){
            binding.title.text=this.volumeInfo.title
            binding.publishDate.text=this.volumeInfo.publishedDate
            binding.description.text=this.volumeInfo.description
            binding.author.text=this.volumeInfo.description
           Glide.with(requireContext()).load(this.volumeInfo.imageLinks?.smallThumbnail)
                .into(binding.thumbnailDetails)

            binding.author.text = volumeInfo.authors?.joinToString(",")
        }*/


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}