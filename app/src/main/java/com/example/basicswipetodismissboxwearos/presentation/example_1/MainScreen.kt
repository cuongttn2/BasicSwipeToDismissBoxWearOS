package com.example.basicswipetodismissboxwearos.presentation.example_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.SplitToggleChip
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChipDefaults

@Composable
fun MainScreen(onNavigateToDetail: () -> Unit) {
    val checked = rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SplitToggleChip(
            checked = checked.value,
            label = { Text("Item details") },
            modifier = Modifier.height(40.dp),
            onCheckedChange = { v -> checked.value = v },
            onClick = onNavigateToDetail,
            toggleControl = {
                Icon(
                    imageVector = ToggleChipDefaults.checkboxIcon(checked = checked.value),
                    contentDescription = null,
                )
            },
        )
    }
}
