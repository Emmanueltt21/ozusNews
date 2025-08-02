package com.kottland.ozusnews.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kottland.ozusnews.domain.model.Article
import com.kottland.ozusnews.domain.usecase.GetBookmarksUseCase
import com.kottland.ozusnews.domain.usecase.AddBookmarkUseCase
import com.kottland.ozusnews.domain.usecase.RemoveBookmarkUseCase
import com.kottland.ozusnews.domain.usecase.IsBookmarkedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    getBookmarksUseCase: GetBookmarksUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
    private val isBookmarkedUseCase: IsBookmarkedUseCase
) : ViewModel() {
    val bookmarks: StateFlow<List<Article>> = getBookmarksUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addBookmark(article: Article) {
        viewModelScope.launch { addBookmarkUseCase(article) }
    }

    fun removeBookmark(article: Article) {
        viewModelScope.launch { removeBookmarkUseCase(article) }
    }

    suspend fun isBookmarked(article: Article): Boolean = isBookmarkedUseCase(article)
}