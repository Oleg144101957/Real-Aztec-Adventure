package com.arbtemey.solo.aztecgold2.ui.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arbtemey.solo.aztecgold2.AztecViewModel
import com.arbtemey.solo.aztecgold2.Constants
import com.arbtemey.solo.aztecgold2.R
import com.arbtemey.solo.aztecgold2.ui.theme.WhitePinkAztec
import kotlin.system.exitProcess


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Launcher(
    navHostController: NavHostController,
    aztecViewModel: AztecViewModel
){
    //Menu

    val fieldTint = remember {
        mutableStateOf("Put your name")
    }

    val buttonsOffsetY = remember { Animatable(0f) }

    LaunchedEffect(Unit){
        buttonsOffsetY.animateTo(targetValue = 196f, tween(
            durationMillis = 300,
            delayMillis = 500,
            easing = FastOutLinearInEasing)
        )
    }

    Image(
        painter = painterResource(id = R.drawable.background_aztec),
        contentDescription = "Menu Panel Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()){

        TextField(
            value = fieldTint.value,
            maxLines = 1,
            onValueChange = {
                aztecViewModel.saveName(it)
                fieldTint.value = it
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.grass),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(modifier = Modifier
            .align(Alignment.Center)
            .offset(y = buttonsOffsetY.value.dp)
        ){
            MenuButton(Buttons.ButtonStart, navHostController)
            MenuButton(Buttons.ButtonExit, navHostController)
        }
    }

    BackHandler{
        //do nothing
    }
}

@Composable
fun MenuButton(button: Buttons, navigationConsole: NavHostController){

    val context = LocalContext.current

    Box(modifier = Modifier.padding(8.dp)){
        Image(
            painter = painterResource(id = R.drawable.btn),
            contentDescription = "button ${button.buttonText}",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    when (button) {

                        is Buttons.ButtonStart -> {
                            navigationConsole.navigate(NavRoutes.DestinationPlayScreen.destination)
                        }

                        is Buttons.ButtonExit -> {
                            val intent = Intent(Intent.ACTION_MAIN)
                            intent.addCategory(Intent.CATEGORY_HOME)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            context.startActivity(intent)
                            exitProcess(0)
                        }
                    }
                }
        )

        Text(
            text = button.buttonText,
            color = WhitePinkAztec,
            fontFamily = Constants.mainFont,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

sealed class Buttons(val buttonText: String){
    object ButtonStart : Buttons("Start")
    object ButtonExit : Buttons("Exit")
}
