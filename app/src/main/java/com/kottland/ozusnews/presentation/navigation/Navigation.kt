package com.kottland.ozusnews.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kottland.ozusnews.domain.model.Article
import com.kottland.ozusnews.presentation.ui.ArticleDetailScreen
import com.kottland.ozusnews.presentation.ui.BookmarksScreen
import com.kottland.ozusnews.presentation.ui.CategoryScreen
import com.kottland.ozusnews.presentation.ui.TopHeadlinesScreen
import com.kottland.ozusnews.presentation.ui.SettingsScreen
import com.kottland.ozusnews.presentation.viewmodel.BookmarksViewModel
import com.kottland.ozusnews.presentation.viewmodel.NewsViewModel
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("top_headlines", "Home", Icons.Filled.Home)
    object Bookmarks : Screen("bookmarks", "Bookmarks", Icons.Filled.Favorite)
    object Settings : Screen("settings", "Settings", Icons.Filled.Settings)
    object Category : Screen("category", "Category", Icons.Filled.Home)
    object ArticleDetail : Screen("article_detail/{title}", "Article", Icons.Filled.Home) {
        fun createRoute(title: String) = "article_detail/$title"
    }
}

@Composable
fun OzusNewsNavHost(
    navController: NavHostController = rememberNavController(),
    viewModel: NewsViewModel = hiltViewModel(),
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    val screens = listOf(Screen.Home, Screen.Bookmarks, Screen.Settings)
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            NavigationBar {
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                TopHeadlinesScreen(
                    viewModel = viewModel,
                    onArticleClick = { article ->
                        navController.navigate(Screen.ArticleDetail.createRoute(article.title ?: ""))
                    },
                    onCategoryClick = {
                        navController.navigate(Screen.Category.route)
                    }
                )
            }
            composable(Screen.Bookmarks.route) {
                val bookmarksViewModel: com.kottland.ozusnews.presentation.viewmodel.BookmarksViewModel = hiltViewModel()
                com.kottland.ozusnews.presentation.ui.BookmarksScreen(
                    viewModel = bookmarksViewModel,
                    onArticleClick = { article ->
                        navController.navigate(Screen.ArticleDetail.createRoute(article.title ?: ""))
                    }
                )
            }
            composable(Screen.Settings.route) {
                SettingsScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeToggle = onThemeToggle
                )
            }
            composable(Screen.Category.route) {
                CategoryScreen(onCategorySelected = { category ->
                    viewModel.fetchTopHeadlines(category = category)
                    navController.popBackStack()
                })
            }
            composable("article_detail/{title}") { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title")
                val article = viewModel.articles.value.find { it.title == title }
                ArticleDetailScreen(article = article)
            }
        }
    }
}