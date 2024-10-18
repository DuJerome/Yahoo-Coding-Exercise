package com.dushanesmith.yahoocodingexercise.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TeamStatsHeaderMainScreen(isMainScreen: Boolean, modifier: Modifier = Modifier) {
    val lastColumnHeader = if(isMainScreen) "Win %" else "T"
    Row(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(75.dp)
            .padding(12.dp)
            .background(Color.Gray)
            .clip(RoundedCornerShape(16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Team Name", modifier = Modifier.padding(end = 72.dp))
        Text(text = "W", modifier = Modifier.padding(end = 42.dp))
        Text(text = "L", modifier = Modifier.padding(end = 42.dp))
        Text(text = "D", modifier = Modifier.padding(end = 42.dp))
        Text(text = lastColumnHeader)
    }
}

@Preview
@Composable
fun preview(){
    TeamStatsHeaderMainScreen(true)
}