package com.arbtemey.solo.aztecgold2.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arbtemey.solo.aztecgold2.Constants
import com.arbtemey.solo.aztecgold2.R
import com.arbtemey.solo.aztecgold2.ui.theme.WhitePinkAztec
import kotlinx.coroutines.delay


@Composable
fun Prepearing(navHostController: NavHostController){

    val percents = remember { mutableIntStateOf(100) }

    LaunchedEffect("percents"){
        delay(500)
        while (percents.value > 0){
            delay(5)
            percents.value = percents.value-1

            if (percents.value == 0){
                navHostController.navigate(NavRoutes.DestinationLauncher.destination)
            }
        }
    }

    Image(
        painter = painterResource(id = R.drawable.background_aztec),
        contentDescription = "Processing Panel Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()){

        Text(
            text = "${percents.value}%",
            color = WhitePinkAztec,
            fontFamily = Constants.mainFont,
            fontSize = 48.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-16).dp)
        )

        Text(
            text = "Processing...",
            color = WhitePinkAztec,
            fontFamily = Constants.mainFont,
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 32.dp)
        )
    }

    BackHandler{
        //do nothing
    }
}
