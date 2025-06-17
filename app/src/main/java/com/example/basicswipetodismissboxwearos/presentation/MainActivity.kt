/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.example.basicswipetodismissboxwearos.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.basicswipetodismissboxwearos.presentation.example_1.SwipeDismissApp_1
import com.example.basicswipetodismissboxwearos.presentation.example_2.SwipeDismissApp_2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            SwipeDismissApp_1()
            SwipeDismissApp_2(navigateBack = {})
        }
    }

}
