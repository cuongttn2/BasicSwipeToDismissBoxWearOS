package com.example.basicswipetodismissboxwearos.presentation.example_2

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.foundation.BasicSwipeToDismissBox
import androidx.wear.compose.foundation.LocalSwipeToDismissBackgroundScrimColor
import androidx.wear.compose.foundation.LocalSwipeToDismissContentScrimColor
import androidx.wear.compose.foundation.edgeSwipeToDismiss
import androidx.wear.compose.foundation.rememberSwipeToDismissBoxState
import androidx.wear.compose.material.Text

/**
Example of using Modifier.edgeSwipeToDismiss with BasicSwipeToDismissBox
 */

@Composable
fun SwipeDismissApp_2(navigateBack: () -> Unit) {

    val state = rememberSwipeToDismissBoxState()

    CompositionLocalProvider(
        LocalSwipeToDismissBackgroundScrimColor provides Color(0xFFFFC107),
        LocalSwipeToDismissContentScrimColor provides Color(0xFFE91E63)
    ) {
// When using Modifier.edgeSwipeToDismiss, it is required that the element on which the
// modifier applies exists within a SwipeToDismissBox which shares the same state.
        BasicSwipeToDismissBox(
            state = state,
            onDismissed = navigateBack
        ) { isBackground ->
            val horizontalScrollState = rememberScrollState(0)
            if (isBackground) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) {
                    Text(
                        modifier =
                            Modifier
                                .align(Alignment.Center),
                        text =
                            "This First screen",
                        color = Color.Black,
                    )
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier =
                            Modifier
                                .align(Alignment.Center)
                                .edgeSwipeToDismiss(state)
                                .horizontalScroll(horizontalScrollState),
                        text =
                            "This text can be scrolled horizontally - to dismiss, swipe " +
                                    "right from the left edge of the screen (called Edge Swiping)",
                        color = Color.Black,
                    )
                }
            }
        }
    }
}
