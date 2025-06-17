/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.example.basicswipetodismissboxwearos.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.BasicSwipeToDismissBox
import androidx.wear.compose.foundation.SwipeToDismissValue
import androidx.wear.compose.foundation.rememberSwipeToDismissBoxState
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.SplitToggleChip
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChipDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Example1()
        }
    }

}

@Composable
fun Example1() {
// State for managing a 2-level navigation hierarchy between
// MainScreen and ItemScreen composables.
// Alternatively, use SwipeDismissableNavHost from wear.compose.navigation.
    var showMainScreen by remember { mutableStateOf(true) }
    val saveableStateHolder = rememberSaveableStateHolder()

// Swipe gesture dismisses ItemScreen to return to MainScreen.
    val state = rememberSwipeToDismissBoxState()
    LaunchedEffect(state.currentValue) {
        if (state.currentValue == SwipeToDismissValue.Dismissed) {
            state.snapTo(SwipeToDismissValue.Default)
            showMainScreen = !showMainScreen
        }
    }

// Hierarchy is ListScreen -> ItemScreen, so we show ListScreen as the background behind
// the ItemScreen, otherwise there's no background to show.
    BasicSwipeToDismissBox(
        state = state,
        userSwipeEnabled = !showMainScreen,
        backgroundKey = if (!showMainScreen) "MainKey" else "Background",
        contentKey = if (showMainScreen) "MainKey" else "ItemKey",
    ) { isBackground ->
        if (isBackground || showMainScreen) {
            // Best practice would be to use State Hoisting and leave this composable stateless.
            // Here, we want to support MainScreen being shown from different destinations
            // (either in the foreground or in the background during swiping) - that can be achieved
            // using SaveableStateHolder and rememberSaveable as shown below.
            saveableStateHolder.SaveableStateProvider(
                key = "MainKey",
                content = {
                    // Composable that maintains its own state
                    // and can be shown in foreground or background.
                    val checked = rememberSaveable { mutableStateOf(true) }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            4.dp, Alignment.CenterVertically
                        ),
                    ) {
                        SplitToggleChip(
                            checked = checked.value,
                            label = { Text("Item details") },
                            modifier = Modifier.height(40.dp),
                            onCheckedChange = { v -> checked.value = v },
                            onClick = { showMainScreen = false },
                            toggleControl = {
                                Icon(
                                    imageVector = ToggleChipDefaults.checkboxIcon(checked = checked.value),
                                    contentDescription = null,
                                )
                            },
                        )
                    }
                },
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("Show details here...", color = MaterialTheme.colors.onPrimary)
                Text("Swipe right to dismiss", color = MaterialTheme.colors.onPrimary)
            }
        }
    }
}
