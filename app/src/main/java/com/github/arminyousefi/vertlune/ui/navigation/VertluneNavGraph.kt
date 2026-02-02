package com.github.arminyousefi.vertlune.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.arminyousefi.vertlune.ui.components.search.SearchOverlay
import com.github.arminyousefi.vertlune.ui.components.SidebarContent
import com.github.arminyousefi.vertlune.ui.components.cart.CartBottomSheet
import com.github.arminyousefi.vertlune.ui.components.snackbar.CustomSnackbar
import com.github.arminyousefi.vertlune.ui.components.snackbar.SnackbarType
import com.github.arminyousefi.vertlune.ui.screens.SplashScreen
import com.github.arminyousefi.vertlune.ui.screens.discover.DiscoverScreen
import com.github.arminyousefi.vertlune.ui.screens.home.HomeScreen
import com.github.arminyousefi.vertlune.ui.screens.shop.ProductFilter
import com.github.arminyousefi.vertlune.ui.screens.shop.ShopScreen
import com.github.arminyousefi.vertlune.ui.screens.shop.ShopViewModel
import com.github.arminyousefi.vertlune.ui.screens.welcome.WelcomeScreen
import kotlinx.coroutines.launch

@Composable
fun VertluneNavGraph() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showSearchOverlay by remember { mutableStateOf(false) }
    val shopViewModel: ShopViewModel = hiltViewModel()
    val uiState by shopViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showCartSheet by remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val enterAnim = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700))
    val exitAnim = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700))

    val showSnackbar: (String, SnackbarType) -> Unit = { message, type ->
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = type.name,

                duration = SnackbarDuration.Short
            )
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SidebarContent(
                currentRoute = currentRoute,
                onCloseClick = { scope.launch { drawerState.close() } }
            ) { route ->
                scope.launch {
                    drawerState.close()
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    ) {

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) { data ->
                    CustomSnackbar(data)

                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Route.Splash.route,
                    enterTransition = { enterAnim },
                    exitTransition = { exitAnim },
                    popEnterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { -it },
                            animationSpec = tween(700)
                        )
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { it },
                            animationSpec = tween(700)
                        )
                    }
                ) {
                    composable(Route.Splash.route) {
                        SplashScreen(onTimeout = {
                            navController.navigate(Route.Welcome.route) {
                                popUpTo(Route.Splash.route) { inclusive = true }
                            }
                        })
                    }

                    composable(Route.Welcome.route) {
                        WelcomeScreen(
                            onExploreClick = { navController.navigate(Route.Home.route) },
                            onMenuClick = { scope.launch { drawerState.open() } },
                            onCartClick = { showCartSheet = true },
                            onSearchClick = { showSearchOverlay = true },
                        )
                    }

                    composable(Route.Home.route) {
                        HomeScreen(
                            onMenuClick = { scope.launch { drawerState.open() } },
                            onCartClick = { showCartSheet = true },
                            onSearchClick = { showSearchOverlay = true },
                        )
                    }

                    composable(Route.Shop.route) {
                        ShopScreen(
                            onBackClick = { navController.popBackStack() },
                            onMenuClick = { scope.launch { drawerState.open() } },
                            onCartClick = { showCartSheet = true },
                            onShowSnackbar = showSnackbar,
                            onSearchClick = { showSearchOverlay = true },
                            viewModel = shopViewModel,
                        )
                    }

                    composable(Route.Discover.route) {

                        DiscoverScreen(
                            onItemClick = { item ->

                                val filter = when (item.tag.lowercase()) {
                                    "short" -> ProductFilter.SHORTS
                                    "top" -> ProductFilter.TOP
                                    "shirt" -> ProductFilter.SHIRT
                                    "sweater" -> ProductFilter.SWEATER
                                    "pants" -> ProductFilter.PANTS
                                    else -> ProductFilter.ALL
                                }

                                shopViewModel.applyFilter(filter)

                                navController.navigate(Route.Shop.route) {

                                    popUpTo(Route.Discover.route) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            onAddClick = {
                            },
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onSearchClick = { showSearchOverlay = true },
                            onCartClick = {
                                showCartSheet = true
                            },
                            onMenuClick = {
                                scope.launch { drawerState.open() }
                            }
                        )
                    }
                }

                if (showCartSheet) {
                    CartBottomSheet(
                        currentRoute = currentRoute,
                        onDismiss = { showCartSheet = false },
                        onNavigateToShop = {
                            showCartSheet = false
                            navController.navigate(Route.Shop.route) {
                                popUpTo(Route.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onCheckoutClick = { showCartSheet = false },
                    )
                }
            }
        }
        SearchOverlay(
            isVisible = showSearchOverlay,
            products = uiState.originalProducts,

            onDismiss = {
                showSearchOverlay = false

            },
            onProductClick = { product ->
                showSearchOverlay = false
                shopViewModel.searchProduct(product.title)

                navController.navigate(Route.Shop.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}