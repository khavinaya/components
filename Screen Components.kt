package com.example.onboardingscreencomponent.Components

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onboardingscreencomponent.OnBoardingItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@Composable
fun TopSection(
    text:String,
    onClick:()->Unit
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = {onClick()},
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = text, color = Color.Red)
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable

fun OnBoardingItems(item: OnBoardingItem, statePager: PagerState) {
    val scope = rememberCoroutineScope()
    val items = OnBoardingItem.get()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
        ) {

        TopSection(text = "Skip") {}
                Image(
                    painter = painterResource(id = item.Image),
                    contentDescription = "Screen1",
                    modifier = Modifier.fillMaxWidth(0.7f)
                )
                HorizontalPagerIndicator(
                    pagerState = statePager,
                    modifier = Modifier
                        .padding(top = 10.dp),
                    activeColor = Color.Red,
                    inactiveColor = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
                )
        Text(
            text = stringResource(id = item.title),
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top=10.dp)
        )

        Text(
            text = stringResource(id = item.text),
            fontSize = 13.sp,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
            textAlign = TextAlign.Center ,
            modifier = Modifier.padding(top=16.dp)
        )


        BottomSection(size = items.size, index = statePager.currentPage) {
            when {
                statePager.currentPage + 1 < items.size -> {

                    scope.launch {
                        statePager.animateScrollToPage(statePager.currentPage + 1)
                    }
                }

            }
        }
    }
}


@ExperimentalPagerApi
@Composable

fun BottomSection(
    size: Int,
    index: Int,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .padding(top=12.dp)
    ) {
        val buttontext = if (size == index + 1) "Get started" else "Next"
        if(size==index+1){
            TopSection(text = "Helo") {
                
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(10))
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
            .background(Color.Red),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = buttontext,
                color = Color.White,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(start = 10.dp))
        }
    }

}




