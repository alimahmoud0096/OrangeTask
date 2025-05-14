package com.alihafez.orangetask.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihafez.orangetask.domain.Result
import com.alihafez.orangetask.data.model.BookItem
import com.alihafez.orangetask.data.model.BooksRes
import com.alihafez.orangetask.domain.onError
import com.alihafez.orangetask.domain.onSuccess
import com.alihafez.orangetask.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: HomeRepository
) : ViewModel() {

    private var _booksStateFlow = MutableStateFlow<List<BookItem>>(arrayListOf())
    val booksStateFlow = _booksStateFlow

    private val _searchQueryFlow = MutableStateFlow("")
    val searchQueryFlow: StateFlow<String> = _searchQueryFlow

    fun updateSearchQuery(query: String) {
        _searchQueryFlow.tryEmit(query)
    }
    init {
        listBooks("Flowers for Algernon")
    }


    fun listBooks(q: String) {
        viewModelScope.launch {
            repo.listBooks(q = q).onSuccess {
                _booksStateFlow.emit(it.items ?: arrayListOf())
            }
        }
    }


}