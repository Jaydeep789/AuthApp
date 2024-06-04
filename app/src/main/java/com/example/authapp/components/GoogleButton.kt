package com.example.authapp.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authapp.R
import com.example.authapp.ui.theme.LoadingBlue

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    shape: Shape = ShapeDefaults.ExtraSmall,
    icon: Int = R.drawable.ic_google_logo,
    loadingIconColor: Color = LoadingBlue,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = Color.LightGray,
    onClick: () -> Unit,
) {

    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(key1 = isLoading) {
        buttonText = if (isLoading) secondaryText else primaryText
    }

    Surface(
        modifier = modifier
            .clickable(enabled = !isLoading) {
                onClick()
            },
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google logo",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = buttonText)
            if (isLoading) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(color = loadingIconColor)
            }
        }
    }
}

@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton {}
}
