package com.kottland.ozusnews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kottland.ozusnews.domain.model.Article
import com.kottland.ozusnews.domain.usecase.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchTopHeadlines(country: String = "us", category: String? = null) {
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            val result = getTopHeadlinesUseCase(country, category)
            result.onSuccess {
                _articles.value = it
            }.onFailure {
                _error.value = it.message
            }
            _isLoading.value = false
        }
    }
}