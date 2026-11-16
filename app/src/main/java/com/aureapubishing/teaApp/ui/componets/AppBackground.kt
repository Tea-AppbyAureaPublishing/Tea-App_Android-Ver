
package com.aureapubishing.teaApp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aureapubishing.teaApp.R
import com.aureapublishing.teaApp.ui.theme.Primary100
import com.aureapublishing.teaApp.ui.theme.Secondary100

@Composable
fun AppBackground(
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary100)
            .clipToBounds()
    ) {
        val columns = (maxWidth / 64.dp).toInt() + 1
        val rows = (maxHeight / 61.dp).toInt() + 1

        val availableWidth = maxWidth

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            repeat(rows) {
                Row(
                    modifier = Modifier.requiredWidth(availableWidth + 64.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(columns) {
                        Image(
                            painter = painterResource(R.drawable.background),
                            contentDescription = null,
                            modifier = Modifier
                                .size(56.dp)
                                .alpha(0.15f),
                            colorFilter = ColorFilter.tint(Secondary100)
                        )
                    }
                }
            }
        }

        content()
    }
}
