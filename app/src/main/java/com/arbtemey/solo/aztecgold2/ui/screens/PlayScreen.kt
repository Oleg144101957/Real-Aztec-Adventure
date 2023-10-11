package com.arbtemey.solo.aztecgold2.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arbtemey.solo.aztecgold2.AztecViewModel
import com.arbtemey.solo.aztecgold2.Constants
import com.arbtemey.solo.aztecgold2.R
import com.arbtemey.solo.aztecgold2.domain.AztecElement
import com.arbtemey.solo.aztecgold2.domain.GameStates
import com.arbtemey.solo.aztecgold2.ui.theme.BlackLightAztec
import com.arbtemey.solo.aztecgold2.ui.theme.WhitePinkAztec
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun PlayScreen(
    navHostController: NavHostController,
    aztecViewModel: AztecViewModel
){
    LaunchedEffect(Unit){
        aztecViewModel.initListOfElements()
    }

    val gameList = aztecViewModel.liveElements.observeAsState()
    val gameScores = aztecViewModel.scores.observeAsState()
    val isVisibleRuresScreen = remember { mutableStateOf(true) }

    val gameTime = remember { mutableIntStateOf(20) }

    val gameStatus = aztecViewModel.status.observeAsState()

    LaunchedEffect(Unit){
        repeat(20){
            delay(1000)
            gameTime.intValue = gameTime.intValue-1
        }
    }

    LaunchedEffect(Unit){
        delay(2000)
        isVisibleRuresScreen.value = false
    }

    //Playground
    Image(
        painter = painterResource(id = R.drawable.background_playscreen),
        contentDescription = "Visual Background for Play Panel ",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )


    Box(modifier = Modifier.fillMaxSize()){

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Button Close",
            tint = WhitePinkAztec,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clickable {
                    navHostController.navigate(NavRoutes.DestinationLauncher.destination)
                }
        )

        GameDetails(
            gameScores = gameScores.value!!,
            time = gameTime.intValue,
            name = aztecViewModel.userName.value!!
        )

        gameList.value?.forEach {
            AnimateElements(it, aztecViewModel)
        }

        if (isVisibleRuresScreen.value){
            RulesScreen()
        }

        if (gameScores.value!! < 0){
            aztecViewModel.postStatus(GameStates.NO_SCORES)
        } else if (gameTime.value!! <= 0){
            aztecViewModel.postStatus(GameStates.NO_TIME)
        } else if (gameScores.value == 10){
            aztecViewModel.postStatus(GameStates.WIN)
        }

        when(gameStatus.value){
            GameStates.NO_SCORES -> { GameOverScreen(msg = "You lose", navigationConsole = navHostController) }
            GameStates.NO_TIME -> { GameOverScreen(msg = "The time is finished", navigationConsole = navHostController) }
            GameStates.WIN -> { YouWinScreen(msg = "You have collect all elements", navigationConsole = navHostController) }
            else -> {  }
        }
    }
}

@Composable
fun RulesScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlackLightAztec.copy(alpha = 0.8f))
    ){

        Column(modifier = Modifier
            .align(Alignment.Center)
        ){

            Text(
                text = "Take care about",
                fontFamily = Constants.mainFont,
                color = WhitePinkAztec,
                fontSize = 18.sp
            )
            Text(
                text = "some elements is harmful for your scores",
                fontFamily = Constants.mainFont,
                color = WhitePinkAztec,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun GameOverScreen(msg: String, navigationConsole: NavHostController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlackLightAztec)
    ){

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Button Close",
            tint = WhitePinkAztec,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clickable {
                    navigationConsole.navigate(NavRoutes.DestinationLauncher.destination)
                }
        )

        Column(modifier = Modifier
            .align(Alignment.Center)
        ){

            Text(
                text = "Game Over!",
                fontFamily = Constants.mainFont,
                color = WhitePinkAztec,
                fontSize = 18.sp
            )
            Text(
                text = msg,
                fontFamily = Constants.mainFont,
                color = WhitePinkAztec,
                fontSize = 18.sp
            )
        }
    }
}


@Composable
fun YouWinScreen(msg: String, navigationConsole: NavHostController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlackLightAztec)
    ){

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Button Close",
            tint = WhitePinkAztec,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clickable {
                    navigationConsole.navigate(NavRoutes.DestinationLauncher.destination)
                }
        )

        Column(modifier = Modifier
            .align(Alignment.Center)
        ){

            Text(
                text = "You Win!",
                fontFamily = Constants.mainFont,
                color = WhitePinkAztec,
                fontSize = 18.sp
            )
            Text(
                text = msg,
                fontFamily = Constants.mainFont,
                color = WhitePinkAztec,
                fontSize = 18.sp
            )
        }
    }
}



@Composable
fun BoxScope.GameDetails(gameScores: Int, time: Int, name: String){
    Box(modifier = Modifier
        .align(Alignment.TopStart)
        .padding(8.dp)

    ){
        Image(
            painter = painterResource(id = R.drawable.plate),
            contentDescription = "details background",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.6f)
        )

        Column(modifier = Modifier
            .align(Alignment.Center)
        ) {

            Text(
                text = "Hello $name",
                color = WhitePinkAztec,
                fontSize = 18.sp,
                fontFamily = Constants.mainFont
            )

            Text(
                text = "Scores is $gameScores",
                color = WhitePinkAztec,
                fontSize = 18.sp,
                fontFamily = Constants.mainFont

            )

            Text(
                text = "Time remains $time",
                color = WhitePinkAztec,
                fontSize = 18.sp,
                fontFamily = Constants.mainFont

            )
        }
    }
}

@Composable
fun BoxScope.AnimateElements(candy: AztecElement, candyJockerViewModel: AztecViewModel){

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp / 2f - 100f
    val screenHeightDp = -(configuration.screenHeightDp - 140f)

    val offsetX = remember {
        Animatable(0f)
    }

    val offsetY = remember {
        Animatable(0f)
    }

    fun getRandomOffsetY() : Float {
        return Random.nextFloat() * screenHeightDp
    }

    fun getRandomOffsetX() : Float {
        return (Random.nextFloat() * screenWidthDp * 2)-screenWidthDp
    }

    LaunchedEffect(Unit){
        offsetX.animateTo(targetValue = getRandomOffsetX(), tween(
            durationMillis = 700,
            delayMillis = 300,
            easing = FastOutLinearInEasing
        )
        )
    }

    LaunchedEffect(Unit){
        offsetY.animateTo(targetValue = getRandomOffsetY(), tween(
            durationMillis = 700,
            delayMillis = 300,
            easing = FastOutLinearInEasing
        )
        )
    }

    if (candy.isVisible){
        Image(
            painter = painterResource(id = candy.image),
            contentDescription = "element",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(48.dp)
                .offset(x = offsetX.value.dp, y = offsetY.value.dp)
                .clickable {
                    candyJockerViewModel.onClickButton(candy.id)
                }
        )
    }
}