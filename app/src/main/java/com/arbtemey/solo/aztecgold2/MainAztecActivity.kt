package com.arbtemey.solo.aztecgold2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arbtemey.solo.aztecgold2.ui.screens.NavigationHub
import com.arbtemey.solo.aztecgold2.ui.theme.RealGoldOfAztecTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainAztecActivity : ComponentActivity() {

    private val aztecViewModel : AztecViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RealGoldOfAztecTheme {
                // A surface container using the 'background' color from the theme
                NavigationHub(aztecViewModel)
            }
        }
    }
}