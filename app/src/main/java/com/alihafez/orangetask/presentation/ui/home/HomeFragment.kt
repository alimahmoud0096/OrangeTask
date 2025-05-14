package com.alihafez.orangetask.presentation.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.alihafez.orangetask.R
import com.alihafez.orangetask.databinding.FragmentHomeBinding
import com.alihafez.orangetask.presentation.ui.home.adapters.BooksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<HomeViewModel>()

    @Inject
    lateinit var booksAdapter: BooksAdapter

    private lateinit var searchView:androidx.appcompat.widget.SearchView


    private val onSearchTextChanged = object : OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
           viewModel.updateSearchQuery((query ?: "").toString())
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel. updateSearchQuery((newText ?: "").toString())
            return true
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupMenu()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        initObservers()
        init()
    }



    private fun setupMenu() {
        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchItem = menu.findItem(R.id.action_search)
                setupSearch(searchItem)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupSearch(menuItem: MenuItem) {
         searchView = menuItem.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = "Search here..."

        //restore the searchview state
        if(!viewModel.searchQueryFlow.value.isBlank()) {
            searchView.isIconified = false;
            searchView.setQuery(viewModel.searchQueryFlow.value, false)
        }
        searchView.setOnQueryTextListener(onSearchTextChanged)



    }

    private fun performSearch(query: String) {
        viewModel.listBooks(query)
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.booksStateFlow.collect {


                booksAdapter.bookList = it
                (view?.parent as? ViewGroup)?.doOnPreDraw {
                    startPostponedEnterTransition()
                }
            }
        }

        // Observe the debounced search query changes
        lifecycleScope.launch {
            try {
                viewModel.searchQueryFlow
                    .debounce(1000)
                    .collect { query ->
                        println("Search query emitted: $query")
                        if (!query.isNullOrBlank())
                            performSearch(query)
                        else
                            performSearch("Flowers for Algernon")

                    }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun init() {

        binding.rv.adapter = booksAdapter
        booksAdapter.onBookClicked = {itemImageView,transitionName,book->
            searchView.setOnQueryTextListener(null)

            val extras = FragmentNavigatorExtras(
                itemImageView to transitionName
            )

            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToNavigationBookDetails(
                    book,
                    transitionName
                ),
                extras
            )
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}