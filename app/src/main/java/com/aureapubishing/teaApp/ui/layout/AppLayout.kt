
package com.aureapubishing.teaApp.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aureapubishing.teaApp.ui.components.AppBackground
import com.aureapubishing.teaApp.ui.components.Header

@Composable
fun AppLayout(
    modifier: Modifier = Modifier,
    showHeader: Boolean = true,
    isProfile: Boolean = false,
    onProfileClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    AppBackground {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            if (showHeader) {
                Header(
                    onProfileClick = onProfileClick,
                    isProfile = isProfile
                )
            }

            content()
        }
    }
}
