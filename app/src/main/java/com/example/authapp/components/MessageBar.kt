package com.example.authapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authapp.domain.model.MessageBarState
import com.example.authapp.ui.theme.AuthAppTheme
import com.example.authapp.ui.theme.ErrorRed
import com.example.authapp.ui.theme.InfoGreen
import kotlinx.coroutines.delay
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun MessageBarComponent(
    messageBarState: MessageBarState,
) {

    var startAnimation by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = messageBarState) {
        if (messageBarState.errorMessage != null) {
            errorMessage = when (messageBarState.errorMessage) {
                is SocketTimeoutException -> {
                    "Connection Timeout Exception"
                }

                is ConnectException -> {
                    "Internet Connection Unavailable"
                }

                else -> {
                    "${messageBarState.errorMessage.message}"
                }
            }
        }
        startAnimation = true
        delay(3000)
        startAnimation = false
    }

    AnimatedVisibility(
        visible = messageBarState.errorMessage != null && startAnimation
                || messageBarState.message != null && startAnimation,
        enter = expandVertically(
            animationSpec = tween(durationMillis = 300),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = 300),
            shrinkTowards = Alignment.Top
        )
    )
    {
        MessageBar(messageBarState = messageBarState, errorMessage)
    }
}

@Composable
fun MessageBar(
    messageBarState: MessageBarState,
    errorMessage: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (messageBarState.errorMessage != null) ErrorRed else InfoGreen)
            .padding(horizontal = 20.dp)
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = if (messageBarState.errorMessage != null)
                Icons.Default.Warning
            else
                Icons.Default.Check,
            contentDescription = "Message Bar Icon",
            tint = Color.White
        )
        Divider(
            modifier = Modifier.width(20.dp),
            color = Color.Transparent
        )
        Text(
            text = if (messageBarState.errorMessage != null)
                errorMessage
            else
                messageBarState.message.toString(),
            color = Color.White,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun MessageBarComponentErrorPreview() {
    AuthAppTheme {
        MessageBar(
            messageBarState = MessageBarState(
                errorMessage = SocketTimeoutException()
            ),
            errorMessage = "Connection Timeout Exception"
        )
    }
}

@Preview
@Composable
fun MessageBarComponentInfoPreview() {
    AuthAppTheme {
        MessageBar(
            messageBarState = MessageBarState(
                message = "Successfully updated information"
            )
        )
    }
}