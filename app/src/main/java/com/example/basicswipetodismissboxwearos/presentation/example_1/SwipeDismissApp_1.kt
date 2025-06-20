package com.example.basicswipetodismissboxwearos.presentation.example_1


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.foundation.BasicSwipeToDismissBox
import androidx.wear.compose.foundation.LocalSwipeToDismissBackgroundScrimColor
import androidx.wear.compose.foundation.LocalSwipeToDismissContentScrimColor
import androidx.wear.compose.foundation.SwipeToDismissValue
import androidx.wear.compose.foundation.rememberSwipeToDismissBoxState

/**
Example of a BasicSwipeToDismissBox with stateful composables:
 */

@Composable
fun SwipeDismissApp_1() {
    var showMainScreen by remember { mutableStateOf(true) }
    val saveableStateHolder = rememberSaveableStateHolder()
    val swipeState = rememberSwipeToDismissBoxState()

    LaunchedEffect(swipeState.currentValue) {
        if (swipeState.currentValue == SwipeToDismissValue.Dismissed) {
            swipeState.snapTo(SwipeToDismissValue.Default)
            showMainScreen = !showMainScreen
        }
    }
    CompositionLocalProvider(
        LocalSwipeToDismissBackgroundScrimColor provides Color(0xFFFFC107),
        LocalSwipeToDismissContentScrimColor provides Color(0xFFE91E63)
    ) {
        BasicSwipeToDismissBox(
            state = swipeState,
            userSwipeEnabled = !showMainScreen,
            backgroundKey = if (!showMainScreen) "MainKey" else "Background",
            contentKey = if (showMainScreen) "MainKey" else "ItemKey"
        ) { isBackground ->
            if (isBackground || showMainScreen) {
                saveableStateHolder.SaveableStateProvider(key = "MainKey") {
                    MainScreen(onNavigateToDetail = { showMainScreen = false })
                }
            } else {
                ItemScreen()
            }
        }
    }
}